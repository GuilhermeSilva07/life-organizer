package com.guilherme.lifeorganizer.model;

import com.guilherme.lifeorganizer.model.enums.FinanceCategory;
import com.guilherme.lifeorganizer.model.enums.FinanceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "finances")
@Getter
@Setter
@NoArgsConstructor
public class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FinanceType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FinanceCategory category;

    @Column(nullable = false)
    private LocalDate date;

    private String notes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.date == null) this.date = LocalDate.now();
    }
}