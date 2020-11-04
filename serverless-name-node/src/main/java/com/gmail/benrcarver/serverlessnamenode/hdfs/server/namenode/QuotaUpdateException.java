package com.gmail.benrcarver.serverlessnamenode.hdfs.server.namenode;

import java.io.IOException;

public class QuotaUpdateException extends IOException {

    public QuotaUpdateException(String message){
        super(message);
    }
}
