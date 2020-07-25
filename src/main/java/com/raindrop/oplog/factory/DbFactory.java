package com.raindrop.oplog.factory;


import com.raindrop.oplog.enums.DbTypeEnum;
import com.raindrop.oplog.store.DbStore;
import com.raindrop.oplog.store.H2DbStore;
import com.raindrop.oplog.store.MysqlDbStore;
import com.raindrop.oplog.store.OracleDbStore;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangliang
 * @date 2020/07/18
 */
public class DbFactory {


    private static Map<DbTypeEnum, DbStore> dbStoreMap;

    static {
        dbStoreMap = new HashMap<>();
        dbStoreMap.put(DbTypeEnum.MYSQL, new MysqlDbStore());
        dbStoreMap.put(DbTypeEnum.H2, new H2DbStore());
        dbStoreMap.put(DbTypeEnum.ORACLE, new OracleDbStore());
    }

    public static DbStore getDbStore(DbTypeEnum type) {
        return dbStoreMap.get(type);
    }

}
