package com.hhy.shop.biz.dal.user.query;

import java.util.Date;

import com.hhy.shop.biz.dal.base.PageQuery;
import com.hhy.shop.biz.dal.user.dataobject.UserDO;

/**
 * 用户分页查询
 *
 * @author hehy
 * @create 2017-06-07
 **/
public class UserPageQuery extends PageQuery<UserDO> {
    private String username;
    private String email;
    private String phone;
    private Integer isDelete;
    private Date gmtStartUpdate;
    private Date gmtEndUpdate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getGmtStartUpdate() {
        return gmtStartUpdate;
    }

    public void setGmtStartUpdate(Date gmtStartUpdate) {
        this.gmtStartUpdate = gmtStartUpdate;
    }

    public Date getGmtEndUpdate() {
        return gmtEndUpdate;
    }

    public void setGmtEndUpdate(Date gmtEndUpdate) {
        this.gmtEndUpdate = gmtEndUpdate;
    }
}
