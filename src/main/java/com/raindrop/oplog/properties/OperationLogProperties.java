package com.raindrop.oplog.properties;

import com.raindrop.oplog.enums.DbTypeEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wangliang
 * @date 2020/07/18
 */
@ConfigurationProperties("op.log")
public class OperationLogProperties {

    /**
     * Whether open operation log. default false.
     */
    private Boolean enable = false;
    /**
     * Whether print operation log. default true.
     */
    private Boolean logging = true;
    /**
     * Operation log storage engine. default MYSQL.
     */
    private DbTypeEnum dbType = DbTypeEnum.MYSQL;
    /**
     * Operation long storage engine table prefix.
     */
    private String dbPrefix;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getLogging() {
        return logging;
    }

    public void setLogging(Boolean logging) {
        this.logging = logging;
    }

    public DbTypeEnum getDbType() {
        return dbType;
    }

    public void setDbType(DbTypeEnum dbType) {
        this.dbType = dbType;
    }

    public String getDbPrefix() {
        return dbPrefix;
    }

    public void setDbPrefix(String dbPrefix) {
        this.dbPrefix = dbPrefix;
    }

}
