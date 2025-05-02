package dev.raniery.register.model.tasks;

import org.springframework.stereotype.Component;

@Component
public class TasksRegisterMapper {

    public Tasks map(TasksRegisterDTO registerDTO) {
        return new Tasks(registerDTO);
    }

    public TasksRegisterDTO mapToDto(Tasks tasks) {
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
