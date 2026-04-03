package com.chinmay.tasker.controller;


import com.chinmay.tasker.dto.CreateTaskRequest;
import com.chinmay.tasker.dto.TaskResponse;
import com.chinmay.tasker.dto.UpdateTaskRequest;
import com.chinmay.tasker.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> fetchAllTasks() {
        return ResponseEntity.ok(taskService.fetchAllTasks());
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody CreateTaskRequest createTaskRequest
            ) {
        return new ResponseEntity<>(taskService.createTask(createTaskRequest), HttpStatus.CREATED  );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> fetchTask(
            @PathVariable UUID id
            ) {
        return ResponseEntity.ok(taskService.fetchTaskById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable UUID id
            ) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTaskRequest updateTaskRequest
    ) {
        final TaskResponse updatedTask = taskService.updateTask(id, updateTaskRequest);
        return ResponseEntity.ok(updatedTask);
    }
}
