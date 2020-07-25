package com.raindrop.oplog.enums;

/**
 * @author wangliang
 * @date 2020/07/18
 */
public enum OpTypeEnum {

    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private String type;

    OpTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
