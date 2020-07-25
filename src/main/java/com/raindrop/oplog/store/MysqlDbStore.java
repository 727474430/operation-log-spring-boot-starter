package com.raindrop.oplog.store;

import com.raindrop.oplog.model.OperationLog;
import com.raindrop.oplog.properties.OperationLogProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * @author wangliang
 * @date 2020/07/18
 */
public class MysqlDbStore implements DbStore {

    private static Logger logger = LoggerFactory.getLogger(MysqlDbStore.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private OperationLogProperties properties;

    @Override
    public OperationLog selectOne(Integer id) {
        String sql = "select id, op_ip, op_type, op_desc, op_request, op_result, op_start_time, op_end_time from operation_log where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, OperationLog.class);
    }

    @Override
    public List<OperationLog> selectAll() {
        String sql = "select id, op_ip, op_type, op_desc, op_request, op_result, op_start_time, op_end_time from operation_log";
        return jdbcTemplate.queryForList(sql, OperationLog.class);
    }

    @Override
    public boolean insert(OperationLog operationLog) {
        String sql = "insert into operation_log(op_ip, op_type, op_desc, op_request, op_result, op_start_time, op_end_time) values " +
                "(?,?,?,?,?,?,?)";
        int result = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, operationLog.getOpIp());
            ps.setString(2, operationLog.getOpType());
            ps.setString(3, operationLog.getOpDesc());
            ps.setString(4, operationLog.getOpRequest());
            ps.setString(5, operationLog.getOpResult());
            ps.setString(6, operationLog.getOpStartTime());
            ps.setString(7, operationLog.getOpEndTime());
            return ps;
        });

        if (properties.getLogging()) {
            logger.info("Operation log starter ---> save op log: \n{}; \nparameter: {}, {}, {}, {}, {}, {}, {}; Operation log end --->",
                    sql, operationLog.getOpIp(), operationLog.getOpType(), operationLog.getOpDesc(), operationLog.getOpRequest(),
                    operationLog.getOpResult(), operationLog.getOpStartTime(), operationLog.getOpEndTime());
        }
        return result > 0;
    }

}
