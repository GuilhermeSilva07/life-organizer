package com.guilherme.lifeorganizer.service;

import com.guilherme.lifeorganizer.dto.FinanceDTO;
import com.guilherme.lifeorganizer.dto.HabitDTO;
import com.guilherme.lifeorganizer.dto.TaskDTO;
import com.guilherme.lifeorganizer.model.enums.TaskStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private FinanceService financeService;

    @Autowired
    private HabitService habitService;

    @Autowired
    private GeminiService geminiService;

    @Transactional
    public String analyzeDailyData(String userQuestion) {
        String context = buildContext();
        String prompt = buildPrompt(context, userQuestion);
        return geminiService.analyze(prompt);
    }

    private String buildContext() {
        StringBuilder context = new StringBuilder();

        List<TaskDTO> pendingTasks = taskService.findByStatus(TaskStatus.PENDING);
        List<TaskDTO> inProgressTasks = taskService.findByStatus(TaskStatus.PROGRESS);
        context.append("TASKS:\n");
        context.append("Pending: ").append(pendingTasks.size()).append("\n");
        pendingTasks.forEach(t -> context
                .append("- ").append(t.title())
                .append(" (priority: ").append(t.priority()).append(")\n"));
        context.append("In progress: ").append(inProgressTasks.size()).append("\n");
        inProgressTasks.forEach(t -> context
                .append("- ").append(t.title()).append("\n"));

        List<FinanceDTO> finances = financeService.findAll();
        context.append("\nFINANCES:\n");
        context.append("Total income: R$ ").append(financeService.getTotalIncome()).append("\n");
        context.append("Total expenses: R$ ").append(financeService.getTotalExpenses()).append("\n");
        context.append("Balance: R$ ").append(financeService.getBalance()).append("\n");
        context.append("Recent transactions: ").append(finances.size()).append("\n");
        finances.stream().limit(5).forEach(f -> context
                .append("- ").append(f.title())
                .append(": R$ ").append(f.amount())
                .append(" (").append(f.type()).append(")\n"));

        List<HabitDTO> habits = habitService.findAll();
        context.append("\nHABITS:\n");
        context.append("Total habits: ").append(habits.size()).append("\n");
        habits.forEach(h -> context
                .append("- ").append(h.name())
                .append(" (").append(h.frequency()).append(")")
                .append(" - completions: ").append(h.completions().size()).append("\n"));

        return context.toString();
    }

    private String buildPrompt(String context, String userQuestion) {
        return """
                You are a personal assistant helping someone organize their life.
                You have access to their personal data below.
                Answer in the same language the user asks the question.
                Be concise, friendly and practical in your responses.
                
                USER DATA:
                %s
                
                USER QUESTION: %s
                
                Provide a helpful and personalized response based on the data above.
                """.formatted(context, userQuestion);
    }
}