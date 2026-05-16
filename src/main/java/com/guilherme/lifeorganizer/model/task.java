package com.guilherme.lifeorganizer.model;

import com.guilherme.lifeorganizer.model.enums.TaskPriority;
import com.guilherme.lifeorganizer.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    private LocalDate duedate;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDate.now();
        if (this.status == null) this.status = TaskStatus.PENDING;
        if (this.priority == null) this.priority = TaskPriority.MEDIUM;
    }
}
