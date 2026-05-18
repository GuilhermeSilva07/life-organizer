package com.guilherme.lifeorganizer.controller;

import com.guilherme.lifeorganizer.dto.TaskDTO;
import com.guilherme.lifeorganizer.dto.TaskRequestDTO;
import com.guilherme.lifeorganizer.model.enums.TaskPriority;
import com.guilherme.lifeorganizer.model.enums.TaskStatus;
import com.guilherme.lifeorganizer.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> findById(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskDTO>> findByStatus(@PathVariable TaskStatus status) {
        return ResponseEntity.ok(service.findByStatus(status));
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskDTO>> findBYPriority(@PathVariable TaskPriority priority){
        return ResponseEntity.ok(service.findByPriority(priority));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> create(@RequestBody @Valid TaskRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable Long id,
                                          @RequestBody @Valid TaskRequestDTO requestDTO){
        return service.update(id, requestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskDTO> updateStatus(@PathVariable Long id,
                                                @RequestParam TaskStatus status){
        return service.updateStatus(id, status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (service.delete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
