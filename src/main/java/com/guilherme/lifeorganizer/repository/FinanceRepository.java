package com.guilherme.lifeorganizer.repository;

import com.guilherme.lifeorganizer.model.Finance;
import com.guilherme.lifeorganizer.model.enums.FinanceCategory;
import com.guilherme.lifeorganizer.model.enums.FinanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long> {

    List<Finance> findByType(FinanceType type);

    List<Finance> findByCategory(FinanceCategory category);

    @Query("SELECT SUM(f.amount) FROM Finance f WHERE f.type = 'INCOME'")
    BigDecimal getTotalIncome();

    @Query("SELECT SUM(f.amount) FROM Finance f WHERE f.type = 'EXPENSE'")
    BigDecimal getTotalExpenses();
}
