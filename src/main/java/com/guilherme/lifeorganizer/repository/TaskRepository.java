package com.guilherme.lifeorganizer.repository;

import com.guilherme.lifeorganizer.model.TaskEntity;
import com.guilherme.lifeorganizer.model.enums.TaskPriority;
import com.guilherme.lifeorganizer.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByStatus(TaskStatus status);

    List<TaskEntity> findByPriority(TaskPriority priority);

    List<TaskEntity> findByStatusOrderByPriorityDesc(TaskStatus status);
}