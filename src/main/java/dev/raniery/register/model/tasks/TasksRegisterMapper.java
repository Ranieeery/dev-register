package dev.raniery.register.model.tasks;

import org.springframework.stereotype.Component;

@Component
public class TasksRegisterMapper {

    public Tasks map(TasksRegisterDTO registerDTO) {
        Tasks tasks = new Tasks();

        tasks.setName(registerDTO.getName());
        tasks.setDescription(registerDTO.getDescription());
        tasks.setLanguage(registerDTO.getLanguage());
        tasks.setStartDate(registerDTO.getStartDate());
        tasks.setDueDate(registerDTO.getDueDate());
        tasks.setDescription(registerDTO.getDescription());

        return tasks;
    }

    public TasksRegisterDTO map(Tasks tasks) {
        TasksRegisterDTO registerDTO = new TasksRegisterDTO();

        registerDTO.setName(tasks.getName());
        registerDTO.setDescription(tasks.getDescription());
        registerDTO.setLanguage(tasks.getLanguage());
        registerDTO.setStartDate(tasks.getStartDate());
        registerDTO.setDueDate(tasks.getDueDate());
        registerDTO.setDescription(tasks.getDescription());

        return registerDTO;
    }
}
