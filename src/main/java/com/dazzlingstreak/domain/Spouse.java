package com.dazzlingstreak.domain;

/**
 * 配偶
 * @author huangdawei
 */
public class Spouse {

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 联系电话
     */
    private String phone;

    public Spouse(String name, String idCard, String phone) {
        this.name = name;
        this.idCard = idCard;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
