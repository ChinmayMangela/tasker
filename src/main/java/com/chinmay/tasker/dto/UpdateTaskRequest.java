package com.chinmay.tasker.dto;

import com.chinmay.tasker.domain.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTaskRequest(
        @NotBlank(message = BLANK_TITLE_ERROR_MESSAGE)
        @Size(max = 50, message = TITLE_SIZE_ERROR_MESSAGE)
        String title,

        @NotBlank(message = BLANK_DESCRIPTION_ERROR_MESSAGE)
        @Size(max = 1000, message = DESCRIPTION_SIZE_ERROR_MESSAGE)
        String description,

        @NotNull(message = TASK_STATUS_ERROR_MESSAGE)
        TaskStatus status
) {

    private final static String BLANK_TITLE_ERROR_MESSAGE = "Title should not be blank";
    private final static String TITLE_SIZE_ERROR_MESSAGE = "Title must be at most 50 characters long";
    private final static String BLANK_DESCRIPTION_ERROR_MESSAGE = "Description should not be blank";
    private final static String DESCRIPTION_SIZE_ERROR_MESSAGE = "Description must be at most 1000 characters long";
    private final static String TASK_STATUS_ERROR_MESSAGE = "Task status is required";

}
