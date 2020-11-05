package com.gmail.benrcarver.serverlessnamenode.hdfs.server.protocol;

import com.gmail.benrcarver.serverlessnamenode.hdfs.server.blockmanagement.BlockInfoContiguous;
import com.gmail.benrcarver.serverlessnamenode.hdfs.server.blockmanagement.HashBuckets;
import com.gmail.benrcarver.serverlessnamenode.hdfs.server.common.HdfsServerConstants;
import com.gmail.benrcarver.serverlessnamenode.hdfs.server.datanode.Replica;
import com.gmail.benrcarver.serverlessnamenode.hdfs.server.protocol.BlockListAsLongs.BlockReportReplica;
import com.google.common.annotations.VisibleForTesting;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.WireFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class BlockReport implements Iterable<BlockReportReplica> {

    private Bucket[] buckets;
    private int numBlocks;

    public Bucket[] getBuckets(){
        return buckets;
    }

    public int getNumberOfBlocks(){
        return numBlocks;
    }

    public BlockReport(Bucket[] buckets, int numBlocks){
        this.buckets = buckets;
        this.numBlocks = numBlocks;
    }

    @VisibleForTesting
    public Iterable<Block> blockIterable() {

        return new Iterable<Block>() {
            @Override
            public Iterator<Block> iterator() {
                return new Iterator<Block>() {
                    Iterator<BlockReportReplica> it = BlockReport.this.iterator();

                    @Override
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override
                    public Block next() {
                        BlockReportReplica next = it.next();
                        return new Block(next.getBlockId(), next.getBytesOnDisk(), next
                                .getGenerationStamp());
                    }
                };
            }
        };
    }

    @Override
    public Iterator<BlockReportReplica> iterator() {
        return new BlockReportIterator();
    }

    public static Builder builder(int numBuckets){
        return new Builder(numBuckets);
    }

    public static int bucket(Replica replica, int numBuckets){
        return bucket(replica.getBlockId(), numBuckets);
    }

    private static int bucket(Block block, int numBuckets){
        return bucket(block.getBlockId(), numBuckets);
    }

    private static int bucket(long blockId, int numBuckets){
        int reminder = (int)(blockId % numBuckets);
        return reminder >= 0 ? reminder : numBuckets + reminder;

    }

    /**
     * Corrupt the generation stamp of the block with the given index.
     * Not meant to be used outside of tests.
     */
//  @VisibleForTesting
//  public BlockReport corruptBlockGSForTesting(final int blockIndex, Random rand) {
//    Builder corruptReportBuilder = builder(buckets.length);
//    int i = 0;
//    for (BlockReportReplica reportedBlock : this){
//      BlockReportReplica toAdd;
//      if (i == blockIndex){
//        toAdd = new BlockReportReplica(reportedBlock.getBlockId(), rand.nextInt(), reportedBlock.getBytesOnDisk(), reportedBlock.getState());
//      } else {
//        toAdd = reportedBlock;
//      }
//      corruptReportBuilder.add(toAdd);
//      i++;
//    }
//    return corruptReportBuilder.build();
//  }

    /**
     * Corrupt the length of the block with the given index by truncation.
     * Not meant to be used outside of tests.
     */
//  @VisibleForTesting
//  public BlockReport corruptBlockLengthForTesting(final int blockIndex, Random rand) {
//    Builder corruptReportBuilder = builder(buckets.length);
//    int i = 0;
//    for (BlockReportReplica reportedBlock : this){
//      BlockReportReplica toAdd;
//      if (i == blockIndex){
//        toAdd = new BlockReportReplica(reportedBlock.getBlockId(), reportedBlock.getGenerationStamp(), rand.nextInt(), reportedBlock.getState());
//      } else {
//        toAdd = reportedBlock;
//      }
//      corruptReportBuilder.add(toAdd);
//      i++;
//    }
//    return corruptReportBuilder.build();
//  }

    private static byte[] hashAsFinalized(Block theBlock) {
        return HashBuckets.hash(theBlock.getBlockId(), theBlock.getGenerationStamp(),
                theBlock.getNumBytes(), HdfsServerConstants.ReplicaState.FINALIZED
                        .getValue());
    }

    public static byte[] hashAsFinalized(BlockReportReplica block){
        Block toHash = new Block(block.getBlockId(), block.getBytesOnDisk(),
                block.getGenerationStamp());
        return hashAsFinalized(toHash);
    }

    public static byte[] hashAsFinalized(BlockInfoContiguous block){
        Block toHash = new Block(block.getBlockId(), block.getNumBytes(),
                block.getGenerationStamp());
        return hashAsFinalized(toHash);
    }

    private static byte[] hash(Replica replica){
        return HashBuckets.hash(replica.getBlockId(), replica.getGenerationStamp(), replica
                .getNumBytes(), replica.getState().getValue());
    }

    public static byte[] hash(Block block, HdfsServerConstants.ReplicaState state){
        return HashBuckets.hash(block.getBlockId(), block.getGenerationStamp(), block
                .getNumBytes(), state.getValue());
    }

    public static class Builder {
        private final int NUM_BUCKETS;
        private ArrayList<Replica>[] buckets;
        private byte[][] hashes;
        private int blockCounter = 0;

        private Builder(int numBuckets) {
            NUM_BUCKETS = numBuckets;
            buckets = new ArrayList[NUM_BUCKETS];
            hashes = new byte[NUM_BUCKETS][HashBuckets.HASH_LENGTH];
            for (int i = 0; i < NUM_BUCKETS; i++) {
                buckets[i] = new ArrayList<>();
            }
        }

//    @VisibleForTesting
//    public Builder add(Replica reportBlock){
//      int bucket = bucket(reportBlock.getBlockId(), NUM_BUCKETS);
//      buckets[bucket].add(reportBlock);
//      HdfsServerConstants.ReplicaState replicaState = null;
//      switch (reportBlock.getState()){
//        case FINALIZED:
//          replicaState = HdfsServerConstants.ReplicaState.FINALIZED;
//          break;
//        case RBW:
//          replicaState = HdfsServerConstants.ReplicaState.RBW;
//          break;
//        case RUR:
//          replicaState = HdfsServerConstants.ReplicaState.RUR;
//          break;
//        case RWR:
//          replicaState = HdfsServerConstants.ReplicaState.RWR;
//          break;
//        case TEMPORARY:
//          replicaState = HdfsServerConstants.ReplicaState.TEMPORARY;
//          break;
//      }
//      byte[] hash = HashBuckets.hash(reportBlock.getBlockId(),reportBlock.getGenerationStamp(),reportBlock.getBytesOnDisk(),
//              replicaState.getValue());
//      HashBuckets.XORHashes(hashes[bucket], hash);
//      blockCounter++;
//      return this;
//    }

        public Builder add(Replica replica) {
            int bucket = bucket(replica, NUM_BUCKETS);
            buckets[bucket].add(replica);
            HashBuckets.XORHashes(hashes[bucket], hash(replica));
            blockCounter++;
            return this;
        }

//    public Builder addAllAsFinalized(List<Block> blocks){
//      for (Block block : blocks){
//        addAsFinalized(block);
//      }
//      return this;
//    }
//
//    public Builder addAsFinalized(Block theBlock) {
//      int bucket = bucket(theBlock, NUM_BUCKETS);
//      buckets[bucket].add(new FinalizedReplica(theBlock, null, null));
//      HashBuckets.XORHashes(hashes[bucket], hashAsFinalized(theBlock));
//      blockCounter++;
//      return this;
//    }


        public BlockReport build(){
            Bucket[] bucketArray = new Bucket[NUM_BUCKETS];
            for (int i = 0; i < NUM_BUCKETS; i++){
                bucketArray[i] = new Bucket(BlockListAsLongs.encode(buckets[i]));
                bucketArray[i].setHash(hashes[i]);
            }
            return new BlockReport(bucketArray, blockCounter);
        }
    }

    private class BlockReportIterator implements Iterator<BlockReportReplica>{

        int currentBucket;
        Iterator<BlockReportReplica> currentBucketIterator;

        BlockReportIterator(){
            currentBucket = 0;
            currentBucketIterator = null;
        }

        @Override
        public boolean hasNext() {
            while (currentBucket < buckets.length){
                if(currentBucketIterator==null){
                    currentBucketIterator = buckets[currentBucket].getBlocks().iterator();
                }
                if(currentBucketIterator.hasNext()){
                    return true;
                } else {
                    currentBucket++;
                    currentBucketIterator = null;
                }
            }
            return false;
        }

        @Override
        public BlockReportReplica next() {
            if (hasNext()) {
                return currentBucketIterator.next();
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not allowed to remove blocks" +
                    " from blockReport.");
        }
    }

    public void writeTo(OutputStream os) throws IOException {
        CodedOutputStream cos = CodedOutputStream.newInstance(os);
        cos.writeInt32(1, buckets.length);
        int fieldId = 2;
        for(Bucket b: buckets){

            cos.writeInt32(fieldId++, b.getBlocks().getNumberOfBlocks());
            cos.writeBytes(fieldId++, b.getBlocks().getBlocksBuffer());
        }
        cos.flush();
    }

    public static BlockReport readFrom(InputStream is) throws IOException {

        CodedInputStream cis = CodedInputStream.newInstance(is);
        int numBuckets = -1;
        Map<Integer, Integer> numBlocksInBucket = new HashMap<>();
        Map<Integer, ByteString> bucketBlocksBuf = new HashMap<>();
        while (!cis.isAtEnd()) {
            int tag = cis.readTag();
            int field = WireFormat.getTagFieldNumber(tag);
            if(field == 0){
                break;
            } else if(field==1){
                numBuckets = (int)cis.readInt32();
            } else if (field > 1 && field % 2==0){
                numBlocksInBucket.put(field/2-1, (int)cis.readInt32());
            } else if (field > 1 && field % 2==1){
                bucketBlocksBuf.put(field/2-1, cis.readBytes());
            }
        }
        if(numBuckets != -1) {
            Bucket[] buckets = new Bucket[numBuckets];
            int numBlocks = 0;
            for(int i=0; i<numBuckets ;i++){
                if (numBlocksInBucket.get(i) != null && bucketBlocksBuf.get(i) != null) {
                    numBlocks += numBlocksInBucket.get(i);
                    BlockListAsLongs blocks = BlockListAsLongs.decodeBuffer(numBlocksInBucket.get(i), bucketBlocksBuf.get(i));
                    buckets[i] = new Bucket(blocks);
                }
            }
            return new BlockReport(buckets, numBlocks);
        }

        return null;
    }
}
