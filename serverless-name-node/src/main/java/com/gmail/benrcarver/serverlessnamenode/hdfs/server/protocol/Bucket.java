package com.gmail.benrcarver.serverlessnamenode.hdfs.server.protocol;

public class Bucket {

    private BlockListAsLongs blocks;

    private byte[] hash;

    private boolean skip = false; // skip processing the bucket

    public Bucket(){}

    public Bucket(BlockListAsLongs blocks){
        this.blocks = blocks;
    }

    public void setBlocks(BlockListAsLongs blocks) {
        this.blocks = blocks;
    }

    public BlockListAsLongs getBlocks() {
        return blocks;
    }

    public void setHash(byte[] hash){
        this.hash = hash;
    }

    public byte[] getHash(){
        return hash;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
}