package com.raindrop.oplog.config;

import com.raindrop.oplog.aop.OperationLogAop;
import com.raindrop.oplog.factory.DbFactory;
import com.raindrop.oplog.properties.OperationLogProperties;
import com.raindrop.oplog.store.DbStore;
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
 * @date 2020/07/18
 */
@Configuration
@ConditionalOnClass(OperationLogAop.class)
@EnableConfigurationProperties(OperationLogProperties.class)
@ConditionalOnProperty(prefix = "op.log", value = "enable", havingValue = "true")
public class OperationLogAutoConfigure {

    private final OperationLogProperties properties;

    public OperationLogAutoConfigure(OperationLogProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnBean(DataSource.class)
    @ConditionalOnMissingBean(OperationLogAop.class)
    public OperationLogAop operationLogAop() {
        return new OperationLogAop();
    }

    @Bean
    @ConditionalOnMissingBean(DbStore.class)
    public DbStore dbStore() {
        return DbFactory.getDbStore(properties.getDbType());
    }

}
