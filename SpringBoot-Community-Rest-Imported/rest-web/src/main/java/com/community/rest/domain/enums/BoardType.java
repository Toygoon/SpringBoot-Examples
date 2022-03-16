package com.community.rest.domain.enums;

public enum BoardType {
    notice("NOTICE"),
    free("FREE");

    private String value;

    BoardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}