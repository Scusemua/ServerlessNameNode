package org.apache.hadoop.hdfs;

import io.hops.metadata.hdfs.entity.EncodingPolicy;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.client.HdfsDataOutputStream;
import org.apache.hadoop.hdfs.protocol.*;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.crypto.key.KeyProviderDelegationTokenExtension;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.hdfs.protocol.EncryptionZone;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.util.Progressable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

/****************************************************************
 * Implementation of the abstract FileSystem for the DFS system.
 * This object is the way end-user code interacts with a Hadoop
 * DistributedFileSystem.
 *
 *****************************************************************/
@InterfaceAudience.LimitedPrivate({ "MapReduce", "HBase" })
@InterfaceStability.Unstable
public class DistributedFileSystem extends FileSystem {
    private Path workingDir;
    private URI uri;
    private String homeDirPrefix =
            DFSConfigKeys.DFS_USER_HOME_DIR_PREFIX_DEFAULT;

    DFSClient dfs;
    private boolean verifyChecksum = true;

    static{
        HdfsConfiguration.init();
    }

    public DistributedFileSystem() {
    }

    private class AlternativeDistributedFileSystem extends DistributedFileSystem {

    }

    public void initialize(URI uri, Configuration conf, InetSocketAddress openWhiskEndpoint) throws IOException {
        super.initialize(uri, conf);
        System.out.println("Calling DistributedFileSystem.initialize() now...");
        getAlternativeSchemeStatistics(getAlternativeScheme(), AlternativeDistributedFileSystem.class, statistics);
        System.out.println("Setting configuration now...");
        setConf(conf);
        System.out.println("Calling uri.getHost() now...");
        String host = uri.getHost();
        if (host == null) {
            throw new IOException("Incomplete HDFS URI, no host: "+ uri);
        }
        homeDirPrefix = conf.get(
                DFSConfigKeys.DFS_USER_HOME_DIR_PREFIX_KEY,
                DFSConfigKeys.DFS_USER_HOME_DIR_PREFIX_DEFAULT);

        System.out.println("Creating DFSClient now...");
        this.dfs = new DFSClient(openWhiskEndpoint, null, conf, statistics);
        this.uri = URI.create(uri.getScheme()+"://"+uri.getAuthority());
        System.out.println("Getting home directory now...");
        this.workingDir = getHomeDirectory();
    }

    @Override
    public void initialize(URI uri, Configuration conf) throws IOException {
        this.initialize(uri, conf, null);
    }

    @Override
    public long getDefaultBlockSize() {
        return dfs.getConf().getDefaultBlockSize();
    }

    @Override
    public short getDefaultReplication() {
        return dfs.getConf().getDefaultReplication();
    }

    @Override
    public Path getHomeDirectory() {
        Path path = new Path(homeDirPrefix + "/" + dfs.ugi.getShortUserName());
        System.out.println("Calling makeQualified with path = \"" + path.toString() + "\"");
        return makeQualified(path);
    }

    @Override
    public BlockLocation[] getFileBlockLocations(FileStatus file, long start,
                                                 long len) throws IOException {
        if (file == null) {
            return null;
        }
        return getFileBlockLocations(file.getPath(), start, len);
    }

    @Override
    public BlockLocation[] getFileBlockLocations(Path p,
                                                 final long start, final long len) throws IOException {
        statistics.incrementReadOps(1);
        final Path absF = fixRelativePart(p);
        return new FileSystemLinkResolver<BlockLocation[]>() {
            @Override
            public BlockLocation[] doCall(final Path p)
                    throws IOException, UnresolvedLinkException {
                return dfs.getBlockLocations(getPathName(p), start, len);
            }
            @Override
            public BlockLocation[] next(final FileSystem fs, final Path p)
                    throws IOException {
                return fs.getFileBlockLocations(p, start, len);
            }
        }.resolve(this, absF);
    }

    /**
     * Enter, leave or get safe mode.
     *
     * @see ClientProtocol#setSafeMode(
     *    HdfsConstants.SafeModeAction,boolean)
     */
    public boolean setSafeMode(HdfsConstants.SafeModeAction action)
            throws IOException {
        return setSafeMode(action, false);
    }

    @InterfaceAudience.Private
    @VisibleForTesting
    public DFSClient getClient() {
        return dfs;
    }

    /**
     * Enter, leave or get safe mode.
     *
     * @param action
     *          One of SafeModeAction.ENTER, SafeModeAction.LEAVE and
     *          SafeModeAction.GET
     * @param isChecked
     *          If true check only for Active NNs status, else check first NN's
     *          status
     * @see ClientProtocol#setSafeMode(HdfsConstants.SafeModeAction, boolean)
     */
    public boolean setSafeMode(HdfsConstants.SafeModeAction action,
                               boolean isChecked) throws IOException {
        return dfs.setSafeMode(action, isChecked);
    }

    @Override
    public URI getUri() {
        return uri;
    }

    /**
     * @see {@link #addCacheDirective(CacheDirectiveInfo, EnumSet)}
     */
    public long addCacheDirective(CacheDirectiveInfo info) throws IOException {
        return addCacheDirective(info, EnumSet.noneOf(CacheFlag.class));
    }

    /**
     * Add a new CacheDirective.
     *
     * @param info Information about a directive to add.
     * @param flags {@link CacheFlag}s to use for this operation.
     * @return the ID of the directive that was created.
     * @throws IOException if the directive could not be added
     */
    public long addCacheDirective(
            CacheDirectiveInfo info, EnumSet<CacheFlag> flags) throws IOException {
        Preconditions.checkNotNull(info.getPath());
        Path path = new Path(getPathName(fixRelativePart(info.getPath()))).
                makeQualified(getUri(), getWorkingDirectory());
        return dfs.addCacheDirective(
                new CacheDirectiveInfo.Builder(info).
                        setPath(path).
                        build(),
                flags);
    }

    /**
     * @see {@link #modifyCacheDirective(CacheDirectiveInfo, EnumSet)}
     */
    public void modifyCacheDirective(CacheDirectiveInfo info) throws IOException {
        modifyCacheDirective(info, EnumSet.noneOf(CacheFlag.class));
    }

    /**
     * Modify a CacheDirective.
     *
     * @param info Information about the directive to modify. You must set the ID
     *          to indicate which CacheDirective you want to modify.
     * @param flags {@link CacheFlag}s to use for this operation.
     * @throws IOException if the directive could not be modified
     */
    public void modifyCacheDirective(
            CacheDirectiveInfo info, EnumSet<CacheFlag> flags) throws IOException {
        if (info.getPath() != null) {
            info = new CacheDirectiveInfo.Builder(info).
                    setPath(new Path(getPathName(fixRelativePart(info.getPath()))).
                            makeQualified(getUri(), getWorkingDirectory())).build();
        }
        dfs.modifyCacheDirective(info, flags);
    }

    /**
     * List cache directives.  Incrementally fetches results from the server.
     *
     * @param filter Filter parameters to use when listing the directives, null to
     *               list all directives visible to us.
     * @return A RemoteIterator which returns CacheDirectiveInfo objects.
     */
    public RemoteIterator<CacheDirectiveEntry> listCacheDirectives(
            CacheDirectiveInfo filter) throws IOException {
        if (filter == null) {
            filter = new CacheDirectiveInfo.Builder().build();
        }
        if (filter.getPath() != null) {
            filter = new CacheDirectiveInfo.Builder(filter).
                    setPath(new Path(getPathName(fixRelativePart(filter.getPath())))).
                    build();
        }
        final RemoteIterator<CacheDirectiveEntry> iter =
                dfs.listCacheDirectives(filter);
        return new RemoteIterator<CacheDirectiveEntry>() {
            @Override
            public boolean hasNext() throws IOException {
                return iter.hasNext();
            }

            @Override
            public CacheDirectiveEntry next() throws IOException {
                // Although the paths we get back from the NameNode should always be
                // absolute, we call makeQualified to add the scheme and authority of
                // this DistributedFilesystem.
                CacheDirectiveEntry desc = iter.next();
                CacheDirectiveInfo info = desc.getInfo();
                Path p = info.getPath().makeQualified(getUri(), getWorkingDirectory());
                return new CacheDirectiveEntry(
                        new CacheDirectiveInfo.Builder(info).setPath(p).build(),
                        desc.getStats());
            }
        };
    }

    /**
     * Add a cache pool.
     *
     * @param info
     *          The request to add a cache pool.
     * @throws IOException
     *          If the request could not be completed.
     */
    public void addCachePool(CachePoolInfo info) throws IOException {
        CachePoolInfo.validate(info);
        dfs.addCachePool(info);
    }

    /**
     * Modify an existing cache pool.
     *
     * @param info
     *          The request to modify a cache pool.
     * @throws IOException
     *          If the request could not be completed.
     */
    public void modifyCachePool(CachePoolInfo info) throws IOException {
        CachePoolInfo.validate(info);
        dfs.modifyCachePool(info);
    }

    /**
     * List all cache pools.
     *
     * @return A remote iterator from which you can get CachePoolEntry objects.
     *          Requests will be made as needed.
     * @throws IOException
     *          If there was an error listing cache pools.
     */
    public RemoteIterator<CachePoolEntry> listCachePools() throws IOException {
        return dfs.listCachePools();
    }

    /**
     * Remove a cache pool.
     *
     * @param poolName
     *          Name of the cache pool to remove.
     * @throws IOException
     *          if the cache pool did not exist, or could not be removed.
     */
    public void removeCachePool(String poolName) throws IOException {
        CachePoolInfo.validateName(poolName);
        dfs.removeCachePool(poolName);
    }

    /**
     * Remove a CacheDirectiveInfo.
     *
     * @param id identifier of the CacheDirectiveInfo to remove
     * @throws IOException if the directive could not be removed
     */
    public void removeCacheDirective(long id)
            throws IOException {
        dfs.removeCacheDirective(id);
    }

    /**
     * Set the per type storage quota of a directory.
     *
     * @param src target directory whose quota is to be modified.
     * @param type storage type of the specific storage type quota to be modified.
     * @param quota value of the specific storage type quota to be modified.
     * Maybe {@link HdfsConstants#QUOTA_RESET} to clear quota by storage type.
     */
    public void setQuotaByStorageType(
            Path src, final StorageType type, final long quota)
            throws IOException {
        Path absF = fixRelativePart(src);
        new FileSystemLinkResolver<Void>() {
            @Override
            public Void doCall(final Path p)
                    throws IOException, UnresolvedLinkException {
                dfs.setQuotaByStorageType(getPathName(p), type, quota);
                return null;
            }
            @Override
            public Void next(final FileSystem fs, final Path p)
                    throws IOException {
                // setQuotaByStorageType is not defined in FileSystem, so we only can resolve
                // within this DFS
                return doCall(p);
            }
        }.resolve(this, absF);
    }

    /** Set a directory's quotas
     * @see ClientProtocol#setQuota(String, long, long, StorageType)
     */
    public void setQuota(Path src, final long namespaceQuota,
                         final long storagespaceQuota) throws IOException {
        Path absF = fixRelativePart(src);
        new FileSystemLinkResolver<Void>() {
            @Override
            public Void doCall(final Path p)
                    throws IOException, UnresolvedLinkException {
                dfs.setQuota(getPathName(p), namespaceQuota, storagespaceQuota);
                return null;
            }
            @Override
            public Void next(final FileSystem fs, final Path p)
                    throws IOException {
                // setQuota is not defined in FileSystem, so we only can resolve
                // within this DFS
                return doCall(p);
            }
        }.resolve(this, absF);
    }

    @Override
    public FSDataInputStream open(Path f, final int bufferSize)
            throws IOException {
        statistics.incrementReadOps(1);
        Path absF = fixRelativePart(f);
        return new FileSystemLinkResolver<FSDataInputStream>() {
            @Override
            public FSDataInputStream doCall(final Path p)
                    throws IOException, UnresolvedLinkException {
                final DFSInputStream dfsis =
                        dfs.open(getPathName(p), bufferSize, verifyChecksum);
                return dfs.createWrappedInputStream(dfsis);
            }
            @Override
            public FSDataInputStream next(final FileSystem fs, final Path p)
                    throws IOException {
                return fs.open(p, bufferSize);
            }
        }.resolve(this, absF);
    }

    /**
     * Create a file that will be erasure-coded asynchronously after creation.
     * Using this method ensures that the file is being written in a way that
     * ensures optimal block placement for the given encoding policy.
     *
     * @param f
     *    the path
     * @param policy
     *    the erasure coding policy to be applied
     * @return
     *    the stream to be written to
     * @throws IOException
     */
    public HdfsDataOutputStream create(Path f, EncodingPolicy policy)
            throws IOException {
        return this.create(f, getDefaultReplication(f),  true, policy);
    }

    @Override
    public FSDataOutputStream create(Path f, FsPermission permission,
                                     boolean overwrite, int bufferSize, short replication, long blockSize,
                                     Progressable progress) throws IOException {
        return this.create(f, permission,
                overwrite ? EnumSet.of(CreateFlag.CREATE, CreateFlag.OVERWRITE)
                        : EnumSet.of(CreateFlag.CREATE), bufferSize, replication,
                blockSize, progress, null);
    }

    /**
     * Same as
     * {@link #create(Path, FsPermission, boolean, int, short, long,
     * Progressable)} with the addition of favoredNodes that is a hint to
     * where the namenode should place the file blocks.
     * The favored nodes hint is not persisted in HDFS. Hence it may be honored
     * at the creation time only. And with favored nodes, blocks will be pinned
     * on the datanodes to prevent balancing move the block. HDFS could move the
     * blocks during replication, to move the blocks from favored nodes. A value
     * of null means no favored nodes for this create
     */
    public HdfsDataOutputStream create(final Path f,
                                       final FsPermission permission, final boolean overwrite,
                                       final int bufferSize, final short replication, final long blockSize,
                                       final Progressable progress, final InetSocketAddress[] favoredNodes,
                                       final EncodingPolicy policy)
            throws IOException {
        statistics.incrementWriteOps(1);
        Path absF = fixRelativePart(f);
        return new FileSystemLinkResolver<HdfsDataOutputStream>() {
            @Override
            public HdfsDataOutputStream doCall(final Path p)
                    throws IOException, UnresolvedLinkException {
                final DFSOutputStream out = dfs.create(getPathName(f), permission,
                        overwrite ? EnumSet.of(CreateFlag.CREATE, CreateFlag.OVERWRITE)
                                : EnumSet.of(CreateFlag.CREATE),
                        true, replication, blockSize, progress, bufferSize, null,
                        favoredNodes, policy);
                return dfs.createWrappedOutputStream(out, statistics);
            }
            @Override
            public HdfsDataOutputStream next(final FileSystem fs, final Path p)
                    throws IOException {
                if (fs instanceof DistributedFileSystem) {
                    DistributedFileSystem myDfs = (DistributedFileSystem)fs;
                    return myDfs.create(p, permission, overwrite, bufferSize, replication,
                            blockSize, progress, favoredNodes, policy);
                }
                throw new UnsupportedOperationException("Cannot create with" +
                        " favoredNodes through a symlink to a non-DistributedFileSystem: "
                        + f + " -> " + p);
            }
        }.resolve(this, absF);
    }

    @Override
    public FSDataOutputStream create(final Path f, final FsPermission permission,
                                     final EnumSet<CreateFlag> cflags, final int bufferSize,
                                     final short replication, final long blockSize, final Progressable progress,
                                     final Options.ChecksumOpt checksumOpt) throws IOException {
        statistics.incrementWriteOps(1);
        Path absF = fixRelativePart(f);
        return new FileSystemLinkResolver<FSDataOutputStream>() {
            @Override
            public FSDataOutputStream doCall(final Path p)
                    throws IOException, UnresolvedLinkException {
                final DFSOutputStream dfsos = dfs.create(getPathName(p), permission,
                        cflags, replication, blockSize, progress, bufferSize,
                        checksumOpt, null);
                return dfs.createWrappedOutputStream(dfsos, statistics);
            }
            @Override
            public FSDataOutputStream next(final FileSystem fs, final Path p)
                    throws IOException {
                return fs.create(p, permission, cflags, bufferSize,
                        replication, blockSize, progress, checksumOpt);
            }
        }.resolve(this, absF);
    }

    /**
     * Create a file that will be erasure-coded asynchronously after creation.
     * Using this method ensures that the file is being written in a way that
     * ensures optimal block placement for the given encoding policy.
     *
     * @param f
     *    the path
     * @param replication
     *    replication
     * @param overwrite
     *    overwrite
     * @param policy
     *    the erasure coding policy to be applied
     * @return
     *    the stream to be written to
     * @throws IOException
     */
    public HdfsDataOutputStream create(Path f, short replication, boolean overwrite, EncodingPolicy
            policy)
            throws IOException {
        return this.create(f, FsPermission.getFileDefault(), overwrite,
                getConf().getInt("io.file.buffer.size", 4096), replication,
                getDefaultBlockSize(f), null, null, policy);
    }

    @Override
    public FSDataOutputStream append(Path f, final int bufferSize,
                                     final Progressable progress) throws IOException {
        return append(f, EnumSet.of(CreateFlag.APPEND), bufferSize, progress);
    }

    /**
     * Append to an existing file (optional operation).
     *
     * @param f the existing file to be appended.
     * @param flag Flags for the Append operation. CreateFlag.APPEND is mandatory
     *          to be present.
     * @param bufferSize the size of the buffer to be used.
     * @param progress for reporting progress if it is not null.
     * @return Returns instance of {@link FSDataOutputStream}
     * @throws IOException
     */
    public FSDataOutputStream append(Path f, final EnumSet<CreateFlag> flag,
                                     final int bufferSize, final Progressable progress) throws IOException {
        statistics.incrementWriteOps(1);
        Path absF = fixRelativePart(f);
        return new FileSystemLinkResolver<FSDataOutputStream>() {
            @Override
            public FSDataOutputStream doCall(final Path p)
                    throws IOException {
                return dfs.append(getPathName(p), bufferSize, flag, progress,
                        statistics);
            }

            @Override
            public FSDataOutputStream next(final FileSystem fs, final Path p)
                    throws IOException {
                return fs.append(p, bufferSize);
            }
        }.resolve(this, absF);
    }

    /**
     * Append to an existing file (optional operation).
     *
     * @param f the existing file to be appended.
     * @param flag Flags for the Append operation. CreateFlag.APPEND is mandatory
     *          to be present.
     * @param bufferSize the size of the buffer to be used.
     * @param progress for reporting progress if it is not null.
     * @param favoredNodes Favored nodes for new blocks
     * @return Returns instance of {@link FSDataOutputStream}
     * @throws IOException
     */
    public FSDataOutputStream append(Path f, final EnumSet<CreateFlag> flag,
                                     final int bufferSize, final Progressable progress,
                                     final InetSocketAddress[] favoredNodes) throws IOException {
        statistics.incrementWriteOps(1);
        Path absF = fixRelativePart(f);
        return new FileSystemLinkResolver<FSDataOutputStream>() {
            @Override
            public FSDataOutputStream doCall(final Path p)
                    throws IOException {
                return dfs.append(getPathName(p), bufferSize, flag, progress,
                        statistics, favoredNodes);
            }
            @Override
            public FSDataOutputStream next(final FileSystem fs, final Path p)
                    throws IOException {
                return fs.append(p, bufferSize);
            }
        }.resolve(this, absF);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean rename(Path src, Path dst) throws IOException {
        statistics.incrementWriteOps(1);

        final Path absSrc = fixRelativePart(src);
        final Path absDst = fixRelativePart(dst);

        // Try the rename without resolving first
        try {
            return dfs.rename(getPathName(absSrc), getPathName(absDst));
        } catch (UnresolvedLinkException e) {
            // Fully resolve the source
            final Path source = getFileLinkStatus(absSrc).getPath();
            // Keep trying to resolve the destination
            return new FileSystemLinkResolver<Boolean>() {
                @Override
                public Boolean doCall(final Path p)
                        throws IOException, UnresolvedLinkException {
                    return dfs.rename(getPathName(source), getPathName(p));
                }
                @Override
                public Boolean next(final FileSystem fs, final Path p)
                        throws IOException {
                    // Should just throw an error in FileSystem#checkPath
                    return doCall(p);
                }
            }.resolve(this, absDst);
        }
    }

    /**
     * This rename operation is guaranteed to be atomic.
     */
    @SuppressWarnings("deprecation")
    @Override
    public void rename(Path src, Path dst, final Options.Rename... options)
            throws IOException {
        statistics.incrementWriteOps(1);
        final Path absSrc = fixRelativePart(src);
        final Path absDst = fixRelativePart(dst);
        // Try the rename without resolving first
        try {
            dfs.rename(getPathName(absSrc), getPathName(absDst), options);
        } catch (UnresolvedLinkException e) {
            // Fully resolve the source
            final Path source = getFileLinkStatus(absSrc).getPath();
            // Keep trying to resolve the destination
            new FileSystemLinkResolver<Void>() {
                @Override
                public Void doCall(final Path p)
                        throws IOException, UnresolvedLinkException {
                    dfs.rename(getPathName(source), getPathName(p), options);
                    return null;
                }
                @Override
                public Void next(final FileSystem fs, final Path p)
                        throws IOException {
                    // Should just throw an error in FileSystem#checkPath
                    return doCall(p);
                }
            }.resolve(this, absDst);
        }
    }

    @Override
    public boolean delete(Path f, boolean recursive) throws IOException {
        return false;
    }

    @Override
    public FileStatus[] listStatus(Path f) throws FileNotFoundException, IOException {
        return new FileStatus[0];
    }

    @Override
    public void setWorkingDirectory(Path new_dir) {

    }

    @Override
    public Path getWorkingDirectory() {
        return null;
    }

    @Override
    public boolean mkdirs(Path f, FsPermission permission) throws IOException {
        return false;
    }

    @Override
    public FileStatus getFileStatus(Path f) throws IOException {
        return null;
    }

    /**
     * Remove a user from group.
     * @param userName
     *            Name of the user.
     * @param groupName
     *            Name of the group.
     * @throws IOException
     */
    public void removeUserFromGroup(String userName, String groupName) throws IOException{
        dfs.removeUserFromGroup(userName, groupName);
    }

    /* HDFS only */
    public void createEncryptionZone(final Path path, final String keyName)
            throws IOException {
        Path absF = fixRelativePart(path);
        new FileSystemLinkResolver<Void>() {
            @Override
            public Void doCall(final Path p) throws IOException,
                    UnresolvedLinkException {
                dfs.createEncryptionZone(getPathName(p), keyName);
                return null;
            }

            @Override
            public Void next(final FileSystem fs, final Path p) throws IOException {
                if (fs instanceof DistributedFileSystem) {
                    DistributedFileSystem myDfs = (DistributedFileSystem) fs;
                    myDfs.createEncryptionZone(p, keyName);
                    return null;
                } else {
                    throw new UnsupportedOperationException(
                            "Cannot call createEncryptionZone"
                                    + " on a symlink to a non-DistributedFileSystem: " + path
                                    + " -> " + p);
                }
            }
        }.resolve(this, absF);
    }

    /**
     * Checks that the passed URI belongs to this filesystem and returns
     * just the path component. Expects a URI with an absolute path.
     *
     * @param file URI with absolute path
     * @return path component of {file}
     * @throws IllegalArgumentException if URI does not belong to this DFS
     */
    private String getPathName(Path file) {
        checkPath(file);
        String result = file.toUri().getPath();
        if (!DFSUtil.isValidName(result)) {
            throw new IllegalArgumentException("Pathname " + result + " from " +
                    file+" is not a valid DFS filename.");
        }
        return result;
    }

    /* HDFS only */
    public EncryptionZone getEZForPath(final Path path)
            throws IOException {
        Preconditions.checkNotNull(path);
        Path absF = fixRelativePart(path);
        return new FileSystemLinkResolver<EncryptionZone>() {
            @Override
            public EncryptionZone doCall(final Path p) throws IOException,
                    UnresolvedLinkException {
                return dfs.getEZForPath(getPathName(p));
            }

            @Override
            public EncryptionZone next(final FileSystem fs, final Path p)
                    throws IOException {
                if (fs instanceof DistributedFileSystem) {
                    DistributedFileSystem myDfs = (DistributedFileSystem) fs;
                    return myDfs.getEZForPath(p);
                } else {
                    throw new UnsupportedOperationException(
                            "Cannot call getEZForPath"
                                    + " on a symlink to a non-DistributedFileSystem: " + path
                                    + " -> " + p);
                }
            }
        }.resolve(this, absF);
    }

    /* HDFS only */
    public RemoteIterator<EncryptionZone> listEncryptionZones()
            throws IOException {
        return dfs.listEncryptionZones();
    }

    @Override
    public void setXAttr(Path path, final String name, final byte[] value,
                         final EnumSet<XAttrSetFlag> flag) throws IOException {
        Path absF = fixRelativePart(path);
        new FileSystemLinkResolver<Void>() {

            @Override
            public Void doCall(final Path p) throws IOException {
                dfs.setXAttr(getPathName(p), name, value, flag);
                return null;
            }

            @Override
            public Void next(final FileSystem fs, final Path p) throws IOException {
                fs.setXAttr(p, name, value, flag);
                return null;
            }
        }.resolve(this, absF);
    }

    @Override
    public byte[] getXAttr(Path path, final String name) throws IOException {
        final Path absF = fixRelativePart(path);
        return new FileSystemLinkResolver<byte[]>() {
            @Override
            public byte[] doCall(final Path p) throws IOException {
                return dfs.getXAttr(getPathName(p), name);
            }
            @Override
            public byte[] next(final FileSystem fs, final Path p)
                    throws IOException, UnresolvedLinkException {
                return fs.getXAttr(p, name);
            }
        }.resolve(this, absF);
    }

    @Override
    public Map<String, byte[]> getXAttrs(Path path) throws IOException {
        final Path absF = fixRelativePart(path);
        return new FileSystemLinkResolver<Map<String, byte[]>>() {
            @Override
            public Map<String, byte[]> doCall(final Path p) throws IOException {
                return dfs.getXAttrs(getPathName(p));
            }
            @Override
            public Map<String, byte[]> next(final FileSystem fs, final Path p)
                    throws IOException, UnresolvedLinkException {
                return fs.getXAttrs(p);
            }
        }.resolve(this, absF);
    }

    @Override
    public Map<String, byte[]> getXAttrs(Path path, final List<String> names)
            throws IOException {
        final Path absF = fixRelativePart(path);
        return new FileSystemLinkResolver<Map<String, byte[]>>() {
            @Override
            public Map<String, byte[]> doCall(final Path p) throws IOException {
                return dfs.getXAttrs(getPathName(p), names);
            }
            @Override
            public Map<String, byte[]> next(final FileSystem fs, final Path p)
                    throws IOException, UnresolvedLinkException {
                return fs.getXAttrs(p, names);
            }
        }.resolve(this, absF);
    }

    @Override
    public List<String> listXAttrs(Path path)
            throws IOException {
        final Path absF = fixRelativePart(path);
        return new FileSystemLinkResolver<List<String>>() {
            @Override
            public List<String> doCall(final Path p) throws IOException {
                return dfs.listXAttrs(getPathName(p));
            }
            @Override
            public List<String> next(final FileSystem fs, final Path p)
                    throws IOException, UnresolvedLinkException {
                return fs.listXAttrs(p);
            }
        }.resolve(this, absF);
    }

    @Override
    public void removeXAttr(Path path, final String name) throws IOException {
        Path absF = fixRelativePart(path);
        new FileSystemLinkResolver<Void>() {
            @Override
            public Void doCall(final Path p) throws IOException {
                dfs.removeXAttr(getPathName(p), name);
                return null;
            }

            @Override
            public Void next(final FileSystem fs, final Path p) throws IOException {
                fs.removeXAttr(p, name);
                return null;
            }
        }.resolve(this, absF);
    }

    @Override
    public Token<?>[] addDelegationTokens(
            final String renewer, Credentials credentials) throws IOException {
        Token<?>[] tokens = super.addDelegationTokens(renewer, credentials);
        if (dfs.isHDFSEncryptionEnabled()) {
            KeyProviderDelegationTokenExtension keyProviderDelegationTokenExtension =
                    KeyProviderDelegationTokenExtension.
                            createKeyProviderDelegationTokenExtension(dfs.getKeyProvider());
            Token<?>[] kpTokens = keyProviderDelegationTokenExtension.
                    addDelegationTokens(renewer, credentials);
            if (tokens != null && kpTokens != null) {
                Token<?>[] all = new Token<?>[tokens.length + kpTokens.length];
                System.arraycopy(tokens, 0, all, 0, tokens.length);
                System.arraycopy(kpTokens, 0, all, tokens.length, kpTokens.length);
                tokens = all;
            } else {
                tokens = (tokens != null) ? tokens : kpTokens;
            }
        }
        return tokens;
    }
}
