package com.raindrop.oplog.model;

import java.io.Serializable;

/**
 * @author wangliang
 * @date 2020/07/18
 */
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 6272539162600414643L;

    private Integer id;
    private String userId;
    private String opIp;
    private String opType;
    private String opDesc;
    private String opRequest;
    private String opResult;
    private String opStartTime;
    private String opEndTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpIp() {
        return opIp;
    }

    public void setOpIp(String opIp) {
        this.opIp = opIp;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getOpDesc() {
        return opDesc;
    }

    public void setOpDesc(String opDesc) {
        this.opDesc = opDesc;
    }

    public String getOpRequest() {
        return opRequest;
    }

    public void setOpRequest(String opRequest) {
        this.opRequest = opRequest;
    }

    public String getOpResult() {
        return opResult;
    }

    public void setOpResult(String opResult) {
        this.opResult = opResult;
    }

    public String getOpStartTime() {
        return opStartTime;
    }

    public void setOpStartTime(String opStartTime) {
        this.opStartTime = opStartTime;
    }

    public String getOpEndTime() {
        return opEndTime;
    }

    public void setOpEndTime(String opEndTime) {
        this.opEndTime = opEndTime;
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", opIp='" + opIp + '\'' +
                ", opType='" + opType + '\'' +
                ", opDesc='" + opDesc + '\'' +
                ", opRequest='" + opRequest + '\'' +
                ", opResult='" + opResult + '\'' +
                ", opStartTime='" + opStartTime + '\'' +
                ", opEndTime='" + opEndTime + '\'' +
                '}';
    }

}
