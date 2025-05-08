package com.switchfully.parkshark.domain.membership;

public enum MembershipType {
    BRONZE("BRONZE"),
    SILVER("SILVER"),
    GOLD("GOLD");

    private final String level;

    MembershipType(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return level;
    }
}
