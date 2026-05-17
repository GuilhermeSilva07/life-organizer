package com.guilherme.lifeorganizer.service;

import com.guilherme.lifeorganizer.dto.FinanceDTO;
import com.guilherme.lifeorganizer.dto.FinanceRequestDTO;
import com.guilherme.lifeorganizer.model.Finance;
import com.guilherme.lifeorganizer.model.enums.FinanceCategory;
import com.guilherme.lifeorganizer.model.enums.FinanceType;
import com.guilherme.lifeorganizer.repository.FinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FinanceService {

    @Autowired
    private FinanceRepository repository;

    public List<FinanceDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public Optional<FinanceDTO> findById(Long id){
        return repository.findById(id)
                .map(this::convertToDTO);
    }

    public List<FinanceDTO> findByType(FinanceType type){
        return repository.findByType(type)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<FinanceDTO> findByCategory(FinanceCategory category){
        return repository.findByCategory(category)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public FinanceDTO create(FinanceRequestDTO requestDTO){
        Finance finance = new Finance();
        finance.setTitle(requestDTO.title());
        finance.setAmount(requestDTO.amount());
        finance.setType(requestDTO.type());
        finance.setCategory(requestDTO.category());
        finance.setDate(requestDTO.date() != null
                ? requestDTO.date()
                : LocalDate.now());
        finance.setNotes(requestDTO.notes());
        return convertToDTO(repository.save(finance));
    }

    public Optional<FinanceDTO> update(Long id, FinanceRequestDTO requestDTO){
        return repository.findById(id)
                .map(finance -> {
                    finance.setTitle(requestDTO.title());
                    finance.setAmount(requestDTO.amount());
                    finance.setType(requestDTO.type());
                    finance.setCategory(requestDTO.category());
                    if (requestDTO.date() != null){
                        finance.setDate(requestDTO.date());
                    }
                    finance.setNotes(requestDTO.notes());
                    return convertToDTO(repository.save(finance));
                });
    }

    public boolean delete(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public BigDecimal getTotalIncome(){
        BigDecimal total = repository.getTotalExpenses();
        return total != null ? total : BigDecimal.ZERO;
    }

    public BigDecimal getTotalExpenses(){
        BigDecimal total = repository.getTotalExpenses();
        return total != null ? total : BigDecimal.ZERO;
    }

    public BigDecimal getBalance(){
        return getTotalIncome().subtract(getTotalExpenses());
    }


    private FinanceDTO convertToDTO(Finance finance){
        return new FinanceDTO(
                finance.getId(),
                finance.getTitle(),
                finance.getAmount(),
                finance.getType(),
                finance.getCategory(),
                finance.getDate(),
                finance.getNotes()
        );
    }
}
