package com.guilherme.lifeorganizer.service;

import com.guilherme.lifeorganizer.dto.TaskDTO;
import com.guilherme.lifeorganizer.dto.TaskRequestDTO;
import com.guilherme.lifeorganizer.model.enums.TaskPriority;
import com.guilherme.lifeorganizer.model.enums.TaskStatus;
import com.guilherme.lifeorganizer.model.TaskEntity;
import com.guilherme.lifeorganizer.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<TaskDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public Optional<TaskDTO> findById(Long id){
        return repository.findById(id)
                .map(this::convertToDTO);
    }

    public List<TaskDTO> findByStatus(TaskStatus status){
        return repository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<TaskDTO> findByPriority(TaskPriority priority){
        return repository.findByPriority(priority)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public TaskDTO create(TaskRequestDTO requestDTO){
        TaskEntity task = new TaskEntity();
        task.setTitle(requestDTO.title());
        task.setDescription(requestDTO.description());
        task.setPriority(requestDTO.priority() != null
                ? requestDTO.priority()
                : TaskPriority.MEDIUM);
        task.setDueDate(requestDTO.duedate());
        return convertToDTO(repository.save(task));
    }

    public Optional<TaskDTO> update(Long id, TaskRequestDTO requestDTO){
        return repository.findById(id)
                .map(taskEntity -> {
                    taskEntity.setTitle(requestDTO.title());
                    taskEntity.setDescription(requestDTO.description());
                    if (requestDTO.priority() != null){
                        taskEntity.setPriority(requestDTO.priority());
                    }
                    taskEntity.setDueDate(requestDTO.duedate());
                    return convertToDTO(repository.save(taskEntity));
                });
    }

    public Optional<TaskDTO> updateStatus(Long id, TaskStatus status){
        return repository.findById(id)
                .map(taskEntity -> {
                    taskEntity.setStatus(status);
                    return convertToDTO(repository.save(taskEntity));
                });
    }

    public boolean delete(Long id){
        if (repository.existsById(id)){
            repository.existsById(id);
            return true;
        }
        return false;
    }

    private TaskDTO convertToDTO(TaskEntity task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.getStatus(),
                task.getDueDate()
        );
    }
}
