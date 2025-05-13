package com.switchfully.parkshark.domain;

public enum AllocationStatus {
    ACTIVE("ACTIVE"),
    FINISHED("FINISHED");

    private final String status;

    AllocationStatus(String status) { this.status = status; }
}
