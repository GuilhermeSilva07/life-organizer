package com.guilherme.lifeorganizer.controller;

import com.guilherme.lifeorganizer.dto.AiRequestDTO;
import com.guilherme.lifeorganizer.dto.AiResponseDTO;
import com.guilherme.lifeorganizer.service.AiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Autowired
    private AiService service;

    @PostMapping("/analyze")
    public ResponseEntity<AiResponseDTO> analyze(@RequestBody @Valid AiRequestDTO requestDTO) {
        String answer = service.analyzeDailyData(requestDTO.question());
        return ResponseEntity.ok(new AiResponseDTO(answer));
    }
}