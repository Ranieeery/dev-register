package dev.raniery.register.model.tasks;

import org.springframework.stereotype.Component;

@Component
public class TasksMapper {

    public Tasks map(TasksDTO tasksDTO) {
        Tasks tasks = new Tasks();

        tasks.setName(tasksDTO.getName());
        tasks.setDescription(tasksDTO.getDescription());
        tasks.setLanguage(tasksDTO.getLanguage());
        tasks.setStartDate(tasksDTO.getStartDate());
        tasks.setDueDate(tasksDTO.getDueDate());
        tasks.setDeveloper(tasksDTO.getDeveloper());
        tasks.setStatus(tasksDTO.getStatus());
        tasks.setCreatedAt(tasksDTO.getCreatedAt());
        tasks.setFinishDate(tasksDTO.getFinishDate());
        tasks.setCompleted(tasksDTO.getCompleted());
        tasks.setPriority(tasksDTO.getPriority());

        return tasks;
    }

    public TasksDTO mapToDto(Tasks tasks) {
        TasksDTO tasksDTO = new TasksDTO();

        tasksDTO.setId(tasks.getId());
        tasksDTO.setName(tasks.getName());
        tasksDTO.setDescription(tasks.getDescription());
        tasksDTO.setLanguage(tasks.getLanguage());
        tasksDTO.setStartDate(tasks.getStartDate());
        tasksDTO.setDueDate(tasks.getDueDate());
        tasksDTO.setDeveloper(tasks.getDeveloper());
        tasksDTO.setStatus(tasks.getStatus());
        tasksDTO.setCreatedAt(tasks.getCreatedAt());
        tasksDTO.setFinishDate(tasks.getFinishDate());
        tasksDTO.setCompleted(tasks.getCompleted());
        tasksDTO.setPriority(tasks.getPriority());

        return tasksDTO;
    }
}
