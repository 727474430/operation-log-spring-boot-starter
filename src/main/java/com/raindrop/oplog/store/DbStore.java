package com.raindrop.oplog.store;

import com.raindrop.oplog.model.OperationLog;

import java.util.List;

/**
 * @author wangliang
 * @date 2020/07/18
 */
public interface DbStore {

    OperationLog selectOne(Integer id);

    List<OperationLog> selectAll();

    boolean insert(OperationLog operationLog);

}
