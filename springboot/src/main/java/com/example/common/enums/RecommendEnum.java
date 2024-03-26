package com.example.common.enums;

public enum RecommendEnum {
    YES("是"),
    NO("否");

    private final String status;

    RecommendEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
