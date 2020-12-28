package com.cyw.info_admin.enumerate;

public enum UserType {
    AMDIN(0, "管理员"),
    USER(1, "普通用户");

    private Integer type;
    private String typeDesc;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    UserType(Integer type, String typeDesc) {
        this.type = type;
        this.typeDesc = typeDesc;
    }

    public static String msg(Integer type) {
        for (UserType m : UserType.values()) {
            if (m.getType() == type) {
                return m.getTypeDesc();
            }
        }
        return "未找到维护中的枚举值";
    }
}
