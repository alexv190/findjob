package com.example.findjob.domain;

import org.apache.commons.lang3.StringUtils;

public enum Role {
    USER("Пользователь"), EMPLOYER("Соискатель"), EMPLOYEE("Работодатель"), ADMIN("Администратор");

    public static Role getRoleFromString(String str_role) {
        try {
            return valueOf(StringUtils.upperCase(str_role));
        } catch (Exception e) {
            return null;
        }
    }

    private String uiName;

    Role(String uiName) {
        this.uiName = uiName;
    }

    public String getUiName() {
        return uiName;
    }
}
