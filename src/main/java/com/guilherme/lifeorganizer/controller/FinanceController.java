package com.guilherme.lifeorganizer.controller;

import com.guilherme.lifeorganizer.dto.FinanceDTO;
import com.guilherme.lifeorganizer.dto.FinanceRequestDTO;
import com.guilherme.lifeorganizer.model.enums.FinanceCategory;
import com.guilherme.lifeorganizer.model.enums.FinanceType;
import com.guilherme.lifeorganizer.service.FinanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/finances")
public class FinanceController {

    @Autowired
    private FinanceService service;

    @GetMapping
    public ResponseEntity<List<FinanceDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinanceDTO> findById(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<FinanceDTO>> findByType(@PathVariable FinanceType type){
        return ResponseEntity.ok(service.findByType(type));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<FinanceDTO>> findByCategory(@PathVariable FinanceCategory category){
        return ResponseEntity.ok(service.findByCategory(category));
    }

    @GetMapping("/summary/income")
    public ResponseEntity<BigDecimal> getTotalIncome() {
        return ResponseEntity.ok(service.getTotalIncome());
    }

    @GetMapping("/summary/expenses")
    public ResponseEntity<BigDecimal> getTotalExpenses() {
        return ResponseEntity.ok(service.getTotalExpenses());
    }

    @GetMapping("/summary/balance")
    public ResponseEntity<BigDecimal> getBalance() {
        return ResponseEntity.ok(service.getBalance());
    }

    @PostMapping
    public ResponseEntity<FinanceDTO> create(@RequestBody @Valid FinanceRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinanceDTO> update(@PathVariable Long id,
                                             @RequestBody @Valid FinanceRequestDTO requestDTO) {
        return service.update(id, requestDTO)
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
