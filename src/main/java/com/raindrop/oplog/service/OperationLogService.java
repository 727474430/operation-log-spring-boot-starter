package com.raindrop.oplog.service;

import com.raindrop.oplog.model.OperationLog;
import com.raindrop.oplog.properties.OperationLogProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;

/**
 * @author wangliang
 */
@Service
public class OperationLogService {

    private static Logger logger = LoggerFactory.getLogger(OperationLogService.class);

    private final JdbcTemplate jdbcTemplate;
    private final OperationLogProperties properties;

    public OperationLogService(JdbcTemplate jdbcTemplate, OperationLogProperties properties) {
        this.jdbcTemplate = jdbcTemplate;
        this.properties = properties;
    }

    /**
     * Save operation log to db
     *
     * @param operationLog
     */
    public void saveOperationLog(OperationLog operationLog) {
        String sql = "insert into operation_log(op_ip, op_type, op_desc, op_request, op_result, op_start_time, op_end_time) values " +
                "(?,?,?,?,?,?,?)";
        if (properties.getLogging()) {
            logger.info("Operation log starter ---> save op log: \n{}; \nparameter: {}, {}, {}, {}, {}, {}, {}; Operation log end --->",
                    sql, operationLog.getOpIp(), operationLog.getOpType(), operationLog.getOpDesc(), operationLog.getOpRequest(),
                    operationLog.getOpResult(), operationLog.getOpStartTime(), operationLog.getOpEndTime());
        }
        jdbcTemplate.update(connection -> {
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
    }

}
