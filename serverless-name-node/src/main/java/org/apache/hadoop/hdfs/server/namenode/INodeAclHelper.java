package org.apache.hadoop.hdfs.server.namenode;

import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.AclException;
import com.gmail.benrcarver.serverlessnamenode.hdfs.server.namenode.AclFeature;
import com.google.common.collect.Lists;
import io.hops.exception.StorageException;
import io.hops.exception.TransactionContextException;
import io.hops.metadata.hdfs.entity.Ace;
import io.hops.transaction.EntityManager;
import org.apache.hadoop.fs.permission.AclEntry;
import org.apache.hadoop.fs.permission.AclEntryScope;
import org.apache.hadoop.fs.permission.AclEntryType;
import org.apache.hadoop.fs.permission.FsAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class INodeAclHelper {
    /**
     *
     * @param inode
     * @return
     * @throws TransactionContextException
     * @throws StorageException
     */
    static com.gmail.benrcarver.serverlessnamenode.hdfs.server.namenode.AclFeature getAclFeature(INode inode) throws TransactionContextException, StorageException, AclException {
        Collection<Ace> result = getAces(inode);
        //Collection<Ace> result = getOwnAces(inode);
        if (result == null){
            return null;
        }
        ArrayList<Ace> asList = Lists.newArrayList(result);
        Collections.sort(asList, Ace.Order.ByIndexAscending);
        return new com.gmail.benrcarver.serverlessnamenode.hdfs.server.namenode.AclFeature(convert(asList));
    }

    /**
     *
     * @param inode
     * @param aclFeature
     * @throws TransactionContextException
     * @throws StorageException
     */
    static void addAclFeature(INode inode, AclFeature aclFeature)
            throws TransactionContextException, StorageException, AclException {
        List<AclEntry> entries = aclFeature.getEntries();

        entries = filterUnnamedNonGroupEntries(entries);
        // checkNoUnnamedDefaults(entries);

        long inodeId = inode.getId();
        for (int i = 0 ; i < entries.size() ; i++){
            EntityManager.update(convert(entries.get(i), inodeId, i));
        }
        inode.setNumAces(entries.size());
    }

    public static void removeAclFeature(INode inode) throws TransactionContextException, StorageException {
        assert inode.getNumAces() > 0;
        Collection<Ace> aces = getOwnAces(inode);
        if (aces == null){
            return;
        }

        for (Ace ace : aces) {
            EntityManager.remove(ace);
        }
        inode.setNumAces(0);
    }

    private static List<AclEntry> filterUnnamedNonGroupEntries(List<AclEntry> entries){
        List<AclEntry> unnamedRemoved = Lists.newArrayList();
        for (AclEntry entry : entries) {
            if (entry.getScope().equals(AclEntryScope.DEFAULT)){
                if (!entry.getType().equals(AclEntryType.GROUP) &&
                        (entry.getName() == null || entry.getName().isEmpty())){
                    continue;
                }
            }
            unnamedRemoved.add(entry);
        }
        return unnamedRemoved;
    }

    private static void checkNoUnnamedDefaults(List<AclEntry> entries) throws AclException {
        for (AclEntry entry : entries) {
            if (entry.getScope().equals(AclEntryScope.DEFAULT) &&
                    (entry.getName() == null || entry.getName().isEmpty())){
                throw new AclException("HOPS does not allow unnamed DEFAULT entries.");
            }
        }
    }

    private static Collection<Ace> getAces(INode inode) throws TransactionContextException, StorageException {
        Collection<Ace> aces;

        if (inode.getNumAces() > 0){
            aces = getOwnAces(inode);
        } else {
            //Check for inherited aces
            aces = getInheritedDefaultAcesAsAccess(inode.getParent());
        }

        return aces;
    }

    private static Collection<Ace> getOwnAces(INode inode) throws TransactionContextException, StorageException {
        assert inode.getNumAces() > 0;
        int[] indices = new int[inode.getNumAces()];
        for (int i = 0 ; i < inode.getNumAces() ; i++){
            indices[i] = i;
        }
        return EntityManager.findList(Ace.Finder.ByInodeIdAndIndices, inode.getId(), indices);
    }

    private static Collection<Ace> getInheritedDefaultAcesAsAccess(INode inode)
            throws TransactionContextException, StorageException {
        if (inode == null){
            return null;
        }

        if(inode.getNumAces() > 0){
            Collection<Ace> ownAces = getOwnAces(inode);
            Collection<Ace> defaultAces = new ArrayList<>();

            for (Ace ownAce : ownAces) {
                if (ownAce.isDefault()){
                    defaultAces.add(ownAce);
                }
            }

            if (!defaultAces.isEmpty()){
                //We found aces to inherit, return them converted
                Collection<Ace> convertedToAccess = new ArrayList<>();
                for (Ace defaultAce : defaultAces) {
                    //if (defaultAce.getSubject() != null && !defaultAce.getSubject().isEmpty()){
                    Ace access = defaultAce.copy();
                    access.setIsDefault(false);
                    convertedToAccess.add(access);
                    //}
                }
                return convertedToAccess;
            }
        }

        //No default aces on this inode, keep traversing
        return getInheritedDefaultAcesAsAccess(inode.getParent());
    }

    private static Ace convert(AclEntry entry, long inodeId, int index) throws AclException {
        return new Ace(inodeId,
                index,
                entry.getName(),
                convert(entry.getType()),
                entry.getScope().equals(AclEntryScope.DEFAULT),
                entry.getPermission().ordinal());
    }

    private static Ace.AceType convert(AclEntryType type) throws AclException {
        switch(type){
            case USER:
                return Ace.AceType.USER;
            case GROUP:
                return Ace.AceType.GROUP;
            case MASK:
                return Ace.AceType.MASK;
            case OTHER:
                return Ace.AceType.OTHER;
            default:
                throw new AclException("Unexpected acl entry type " + type.toString()
                        + ", should be USER, GROUP, MASK or " + "OTHER");
        }
    }

    private static AclEntryType convert(Ace.AceType type) throws AclException {
        switch(type){
            case USER:
                return AclEntryType.USER;
            case GROUP:
                return AclEntryType.GROUP;
            case MASK:
                return AclEntryType.MASK;
            case OTHER:
                return AclEntryType.OTHER;
            default:
                throw new AclException("Unexpected ace type " + type.toString()
                        + ", should be USER, GROUP, MASK or OTHER");

        }
    }

    public static List<AclEntry> convert(List<Ace> aces) throws AclException {
        List<AclEntry> result = new ArrayList<>();
        for (Ace ace : aces) {
            result.add(new AclEntry.Builder()
                    .setScope(ace.isDefault()?AclEntryScope.DEFAULT:AclEntryScope.ACCESS)
                    .setName(ace.getSubject())
                    .setPermission(FsAction.values()[ace.getPermission()])
                    .setType(convert(ace.getType())).build());
        }
        return result;
    }
}
