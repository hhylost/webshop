package com.hhy.shop.common.auditlog.bo;

/**
 * @author hehy
 * @create 2017-03-28
 **/
public class AuditLogBO {
    /**
     * 具体操作名称
     */
    private String operateName;
    /**
     * 操作详情
     */
    private String operateDetail;

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getOperateDetail() {
        return operateDetail;
    }

    public void setOperateDetail(String operateDetail) {
        this.operateDetail = operateDetail;
    }
}
