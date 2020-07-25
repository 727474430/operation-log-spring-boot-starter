package com.raindrop.oplog.service;

import com.raindrop.oplog.model.OperationLog;
import com.raindrop.oplog.store.DbStore;
import org.springframework.stereotype.Service;

/**
 * @author wangliang
 * @date 2020/07/18
 */
@Service
public class OperationLogService {

    private final DbStore dbStore;

    public OperationLogService(DbStore dbStore) {
        this.dbStore = dbStore;
    }

    /**
     * Save operation log to db
     *
     * @param operationLog
     */
    public void saveOperationLog(OperationLog operationLog) {
        dbStore.insert(operationLog);
    }

}
