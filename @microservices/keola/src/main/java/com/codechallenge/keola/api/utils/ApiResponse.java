package com.codechallenge.keola.api.utils;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {

    @Getter
    private boolean success;
    @Getter
    private String message;
    @Getter
    private Object data;
    @Getter
    private Map<String, String> errors;

    public ApiResponse(boolean success, String message, Object data, Map<String, String> errors) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public static  ApiResponse createErrorResponse(String message, String errorCode) {
        Map<String, String> errors = new HashMap<>();
        errors.put("code", errorCode);
        return new ApiResponse(false, message, null, errors);
    }

    public static ApiResponse createSuccessResponse(String message, Object data) {
        return new ApiResponse(true, message, data, null);
    }
}
