package com.guilherme.lifeorganizer.repository;

import com.guilherme.lifeorganizer.model.Habit;
import com.guilherme.lifeorganizer.model.enums.HabitFrequency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    List<Habit> findByFrequency(HabitFrequency frequency);
}
