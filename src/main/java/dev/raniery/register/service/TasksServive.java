package dev.raniery.register.service;

import dev.raniery.register.model.tasks.TasksRegisterMapper;
import dev.raniery.register.repository.TasksRepository;
import org.springframework.stereotype.Service;

@Service
public class TasksServive {

    private final TasksRepository tasksRepository;
    private final TasksRegisterMapper tasksRegisterMapper;

    public TasksServive(TasksRepository tasksRepository, TasksRegisterMapper tasksRegisterMapper) {
        this.tasksRepository = tasksRepository;
        this.tasksRegisterMapper = tasksRegisterMapper;
    }

    // public List<TasksRegisterDTO> listTasks() {
    // return tasksRepository.findAllBy
    // }

}
