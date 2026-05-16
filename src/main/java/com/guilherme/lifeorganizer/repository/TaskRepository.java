package com.guilherme.lifeorganizer.repository;

import com.guilherme.lifeorganizer.model.enums.TaskPriority;
import com.guilherme.lifeorganizer.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByPriority(TaskPriority priority);

    List<Task> findByStatusOrderByPriorityDesc(TaskStatus status);

}
