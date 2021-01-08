package org.apache.hadoop.hdfs.protocol;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.fs.StorageType;

import static org.apache.hadoop.util.StringUtils.TraditionalBinaryPrefix.long2String;

@InterfaceAudience.Private
@InterfaceStability.Evolving
public class QuotaByStorageTypeExceededException extends QuotaExceededException {
    protected static final long serialVersionUID = 1L;
    protected StorageType type;

    public QuotaByStorageTypeExceededException() {}

    public QuotaByStorageTypeExceededException(String msg) {
        super(msg);
    }

    public QuotaByStorageTypeExceededException(long quota, long count, StorageType type) {
        super(quota, count);
        this.type = type;
    }

    @Override
    public String getMessage() {
        String msg = super.getMessage();
        if (msg == null) {
            return "Quota by storage type : " + type.toString() +
                    " on path : " + (pathName==null ? "": pathName) +
                    " is exceeded. quota = "  + long2String(quota, "B", 2) +
                    " but space consumed = " + long2String(count, "B", 2);
        } else {
            return msg;
        }
    }
}
