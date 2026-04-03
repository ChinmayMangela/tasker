package com.chinmay.tasker.mapper;

import com.chinmay.tasker.domain.entity.TaskEntity;
import com.chinmay.tasker.dto.TaskResponse;

public class TaskMapper {

    public static TaskResponse toResponse(TaskEntity entity) {
        return new TaskResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getTaskStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
