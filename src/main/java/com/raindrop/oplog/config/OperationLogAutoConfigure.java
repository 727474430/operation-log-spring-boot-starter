package com.raindrop.oplog.config;

import com.raindrop.oplog.aop.OperationLogAop;
import com.raindrop.oplog.properties.OperationLogProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author wangliang
 */
@Configuration
@ConditionalOnClass(OperationLogAop.class)
@EnableConfigurationProperties(OperationLogProperties.class)
@ConditionalOnProperty(prefix = "op.log", value = "enable", havingValue = "true")
public class OperationLogAutoConfigure {

    @Bean
    @ConditionalOnBean(DataSource.class)
    @ConditionalOnMissingBean(OperationLogAop.class)
    public OperationLogAop operationLogAop() {
        return new OperationLogAop();
    }

}
