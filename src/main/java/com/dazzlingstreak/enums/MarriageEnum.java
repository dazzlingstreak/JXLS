package com.dazzlingstreak.enums;

/**
 * MarriageEnum
 * @author huangdawei
 */
public enum MarriageEnum {
    UNKNOWN(0,"未知"),
    UNMARRIED(1,"未婚"),
    MARRIED(2,"已婚"),
    OTHER(3,"其他")
    ;

    MarriageEnum(int code, String name) {
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
