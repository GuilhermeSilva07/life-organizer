package com.guilherme.lifeorganizer.repository;

import com.guilherme.lifeorganizer.model.Habit;
import com.guilherme.lifeorganizer.model.enums.HabitFrequency;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

    @EntityGraph(attributePaths = {"completions"})
    List<Habit> findAll();

    @EntityGraph(attributePaths = {"completions"})
    Optional<Habit> findById(Long id);

    @EntityGraph(attributePaths = {"completions"})
    List<Habit> findByFrequency(HabitFrequency frequency);
}