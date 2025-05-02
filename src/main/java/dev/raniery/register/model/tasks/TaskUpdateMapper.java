package dev.raniery.register.model.tasks;

import org.springframework.stereotype.Component;

@Component
public class TaskUpdateMapper {
    public Tasks map(TasksUpdateDTO updateDTO) {
        Tasks tasks = new Tasks();

        tasks.setName(updateDTO.getName());
        tasks.setDescription(updateDTO.getDescription());
        tasks.setLanguage(updateDTO.getLanguage());
        tasks.setStartDate(updateDTO.getStartDate());
        tasks.setDueDate(updateDTO.getDueDate());
        tasks.setDeveloper(updateDTO.getDeveloper());
        tasks.setStatus(updateDTO.getStatus());
        tasks.setFinishDate(updateDTO.getFinishDate());
        tasks.setPriority(updateDTO.getPriority());

        return tasks;
    }

    public TasksUpdateDTO map(Tasks tasks) {
        TasksUpdateDTO tasksUpdateDTO = new TasksUpdateDTO();

        tasksUpdateDTO.setName(tasks.getName());
        tasksUpdateDTO.setDescription(tasks.getDescription());
        tasksUpdateDTO.setLanguage(tasks.getLanguage());
        tasksUpdateDTO.setStartDate(tasks.getStartDate());
        tasksUpdateDTO.setDueDate(tasks.getDueDate());
        tasksUpdateDTO.setDeveloper(tasks.getDeveloper());
        tasksUpdateDTO.setStatus(tasks.getStatus());
        tasksUpdateDTO.setFinishDate(tasks.getFinishDate());
        tasksUpdateDTO.setPriority(tasks.getPriority());

        return tasksUpdateDTO;
    }
}
