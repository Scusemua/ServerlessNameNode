package com.gmail.benrcarver.Fission.NameNode;

import java.io.IOException;

public class QuotaUpdateException extends IOException {

    public QuotaUpdateException(String message){
        super(message);
    }
}
