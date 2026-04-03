package com.chinmay.tasker.service;

import com.chinmay.tasker.domain.entity.TaskEntity;
import com.chinmay.tasker.dto.CreateTaskRequest;
import com.chinmay.tasker.dto.TaskResponse;
import com.chinmay.tasker.dto.UpdateTaskRequest;
import com.chinmay.tasker.exception.TaskNotFoundException;
import com.chinmay.tasker.mapper.TaskMapper;
import com.chinmay.tasker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse createTask(CreateTaskRequest createTaskRequest) {
        final TaskEntity task = new TaskEntity();
        task.setTitle(createTaskRequest.title());
        task.setDescription(createTaskRequest.description());
        task.setTaskStatus(createTaskRequest.status());

        final TaskEntity savedTask = taskRepository.save(task);
        return TaskMapper.toResponse(savedTask);
    }

    public List<TaskResponse> fetchAllTasks() {
        return taskRepository
                .findAll()
                .stream()
                .map(TaskMapper::toResponse)
                .toList();
    }

    public TaskResponse fetchTaskById(
            UUID id
    ) {
        return taskRepository
                .findById(id)
                .map(TaskMapper::toResponse)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public void deleteTaskById(
            UUID id
    ) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }

        taskRepository.deleteById(id);
    }

    public TaskResponse updateTask(
            UUID id,
            UpdateTaskRequest updateTaskRequest
    ) {
        TaskEntity taskToUpdate = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        taskToUpdate.setTitle(updateTaskRequest.title());
        taskToUpdate.setDescription(updateTaskRequest.description());
        taskToUpdate.setTaskStatus(updateTaskRequest.status());

        TaskEntity updatedTask = taskRepository.save(taskToUpdate);
        return TaskMapper.toResponse(updatedTask);
    }
}
