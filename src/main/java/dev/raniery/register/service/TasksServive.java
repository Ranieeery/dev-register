package dev.raniery.register.service;

import dev.raniery.register.model.tasks.*;
import dev.raniery.register.repository.TasksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TasksServive {

    private final TasksRepository tasksRepository;
    private final TasksMapper tasksMapper;
    private final TasksRegisterMapper tasksRegisterMapper;


    public TasksServive(TasksRepository tasksRepository, TasksMapper tasksMapper, TasksRegisterMapper tasksRegisterMapper) {
        this.tasksRepository = tasksRepository;
        this.tasksMapper = tasksMapper;
        this.tasksRegisterMapper = tasksRegisterMapper;
    }

    public Tasks createTask(TasksRegisterDTO tasksRegisterDTO) {
        Tasks task = tasksRegisterMapper.map(tasksRegisterDTO);

        return tasksRepository.save(task);
    }

    public Page<TasksDTO> findAll(Pageable pageable) {
        Page<Tasks> tasksPage = tasksRepository.findAllByCompletedTrue(pageable);

        return tasksPage.map(tasksMapper::mapToDto);
    }
}
