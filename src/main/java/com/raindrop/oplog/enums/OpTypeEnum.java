package com.raindrop.oplog.enums;

/**
 * @author wangliang
 */
public enum OpTypeEnum {

    ADD("新增"),
    UPDATE("更新"),
    DELETE("删除");

    private String type;

    OpTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
