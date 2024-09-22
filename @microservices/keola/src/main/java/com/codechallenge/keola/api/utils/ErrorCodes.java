package com.codechallenge.keola.api.utils;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    CUSTOMER_EXISTS("CUSTOMER_EXISTS"),
    CUSTOMER_NOT_UPDATED("CUSTOMER_NOT_UPDATED"),
    CUSTOMER_NOT_FOUND("CUSTOMER_NOT_FOUND"),
    PRODUCT_EXISTS("PRODUCT_EXISTS"),
    PRODUCT_NOT_UPDATED("PRODUCT_NOT_UPDATED"),
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND");

    private final String code;

    ErrorCodes(String code) {
        this.code = code;
    }
}
