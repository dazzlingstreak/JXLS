package com.dazzlingstreak.enums;

/**
 * GenderEnum
 * @author huangdawei
 */
public enum GenderEnum {
    UNKNOWN(0,"未知"),
    MALE(1,"男"),
    FEMALE(2,"女")
    ;

    GenderEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int code;
    private String name;
}
