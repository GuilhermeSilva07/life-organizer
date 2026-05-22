package com.guilherme.lifeorganizer.model;

import com.guilherme.lifeorganizer.model.enums.HabitFrequency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "habits")
@Getter
@Setter
@NoArgsConstructor
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HabitFrequency frequency;

    @ElementCollection
    @CollectionTable(name = "habit_completions",
            joinColumns = @JoinColumn(name = "habit_id"))
    @Column(name = "completion_date")
    private List<LocalDate> completions = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        if (this.frequency == null) this.frequency = HabitFrequency.DAILY;
    }
}
