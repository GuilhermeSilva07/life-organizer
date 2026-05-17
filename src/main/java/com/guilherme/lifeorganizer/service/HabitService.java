package com.guilherme.lifeorganizer.service;

import com.guilherme.lifeorganizer.dto.HabitDTO;
import com.guilherme.lifeorganizer.dto.HabitRequestDTO;
import com.guilherme.lifeorganizer.model.Habit;
import com.guilherme.lifeorganizer.model.enums.HabitFrequency;
import com.guilherme.lifeorganizer.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HabitService {

    @Autowired
    private HabitRepository repository;

    public List<HabitDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public Optional<HabitDTO> findById(Long id){
        return repository.findById(id)
                .map(this::convertToDTO);
    }

    public List<HabitDTO> findByFrequency(HabitFrequency frequency){
        return repository.findByFrequency(frequency)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public HabitDTO create(HabitRequestDTO requestDTO){
        Habit habit = new Habit();
        habit.setName(requestDTO.name());
        habit.setDescription(requestDTO.description());
        habit.setFrequency(requestDTO.Frequency());
        return convertToDTO(repository.save(habit));
    }

    public Optional<HabitDTO> update(Long id, HabitRequestDTO requestDTO) {
        return repository.findById(id)
                .map(habit -> {
                    habit.setName(requestDTO.name());
                    habit.setDescription(requestDTO.description());
                    habit.setFrequency(requestDTO.Frequency());
                    return convertToDTO(repository.save(habit));
                });
    }

    public Optional<HabitDTO> completeToday(Long id) {
        return repository.findById(id)
                .map(habit -> {
                    LocalDate today = LocalDate.now();
                    if (!habit.getCompletions().contains(today)) {
                        habit.getCompletions().add(today);
                        repository.save(habit);
                    }
                    return convertToDTO(habit);
                });
    }

    public boolean delete(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private HabitDTO convertToDTO(Habit habit){
        return new HabitDTO(
                habit.getId(),
                habit.getName(),
                habit.getDescription(),
                habit.getFrequency(),
                habit.getCompletions()
        );
    }
}
