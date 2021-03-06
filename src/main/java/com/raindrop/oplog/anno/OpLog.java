package com.raindrop.oplog.anno;

import com.raindrop.oplog.enums.OpTypeEnum;

import java.lang.annotation.*;

/**
 * @author wangliang
 * @date 2020/07/18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpLog {

    /**
     * Operation description
     *
     * @return
     */
    String value() default "";

    /**
     * Operation type
     *
     * @return
     */
    OpTypeEnum type() default OpTypeEnum.INSERT;

}
