package com.dazzlingstreak.domain;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/6.
 */
public class Employee {

    /**
     * 姓名
     */
    private String name;

    /**
     * 是否已婚
     */
    private Boolean marriage;

    /**
     * 出生年月
     */
    private Date birthday;

    /**
     * 手机号
     */
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMarriage() {
        return marriage;
    }

    public void setMarriage(Boolean marriage) {
        this.marriage = marriage;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
