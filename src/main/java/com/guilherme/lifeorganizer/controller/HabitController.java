package com.guilherme.lifeorganizer.controller;

import com.guilherme.lifeorganizer.dto.HabitDTO;
import com.guilherme.lifeorganizer.dto.HabitRequestDTO;
import com.guilherme.lifeorganizer.model.enums.HabitFrequency;
import com.guilherme.lifeorganizer.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    @Autowired
    private HabitService service;

    @GetMapping
    public ResponseEntity<List<HabitDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitDTO> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/frequency/{frequency}")
    public ResponseEntity<List<HabitDTO>> findByFrequency(@PathVariable HabitFrequency frequency) {
        return ResponseEntity.ok(service.findByFrequency(frequency));
    }

    @PostMapping
    public ResponseEntity<HabitDTO> create(@RequestBody @Valid HabitRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitDTO> update(@PathVariable Long id,
                                           @RequestBody @Valid HabitRequestDTO requestDTO) {
        return service.update(id, requestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<HabitDTO> completeToday(@PathVariable Long id) {
        return service.completeToday(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}