package com.switchfully.parkshark.domain.user;

public enum UserRole {
    MEMBER("MEMBER"),
    MANAGER("MANAGER");

    private final String roleValue;

    UserRole(String roleValue) {
        this.roleValue = roleValue;
    }

    @Override
    public String toString() {
        return roleValue;
    }
}
