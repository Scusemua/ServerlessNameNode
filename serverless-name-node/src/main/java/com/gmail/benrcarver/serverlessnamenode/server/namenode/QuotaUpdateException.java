package com.gmail.benrcarver.serverlessnamenode.server.namenode;

import java.io.IOException;

public class QuotaUpdateException extends IOException {

    public QuotaUpdateException(String message){
        super(message);
    }
}
