package com.chinmay.tasker.dto.exception;

import java.util.Map;

public record ErrorResponseDto(
        int status,
        String message,
        long timestamp,
        Map<String, String> errors
) {
}