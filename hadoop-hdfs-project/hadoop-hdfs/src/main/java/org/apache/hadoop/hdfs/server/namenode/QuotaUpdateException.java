package namenode;

import java.io.IOException;

public class QuotaUpdateException extends IOException {

    public QuotaUpdateException(String message){
        super(message);
    }
}
