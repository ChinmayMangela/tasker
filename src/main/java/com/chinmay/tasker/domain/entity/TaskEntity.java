package com.chinmay.tasker.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "Title is required")
    @Size(max = 50, message = "Title at most 50 characters long")
    @Column(nullable = false, length = 50)
    private String title;

    @NotNull(message = "Description is required")
    @Size(max = 1000, message = "Description at most 1000 characters long")
    @Column(nullable = false, length = 1000)
    private String description;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private Status taskStatus;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
