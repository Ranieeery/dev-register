package dev.raniery.register.service;

import dev.raniery.register.model.tasks.*;
import dev.raniery.register.repository.DeveloperRepository;
import dev.raniery.register.repository.TasksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TasksServive {

    private final TasksRepository tasksRepository;
    private final TasksMapper tasksMapper;
    private final TasksRegisterMapper tasksRegisterMapper;
    private final DeveloperRepository developerRepository;
    private final TaskUpdateMapper taskUpdateMapper;


    public TasksServive(TasksRepository tasksRepository, TasksMapper tasksMapper, TasksRegisterMapper tasksRegisterMapper, DeveloperRepository developerRepository, TaskUpdateMapper taskUpdateMapper) {
        this.tasksRepository = tasksRepository;
        this.tasksMapper = tasksMapper;
        this.tasksRegisterMapper = tasksRegisterMapper;
        this.developerRepository = developerRepository;
        this.taskUpdateMapper = taskUpdateMapper;
    }

    public Tasks createTask(TasksRegisterDTO tasksRegisterDTO) {
        Tasks task = tasksRegisterMapper.map(tasksRegisterDTO);

        return tasksRepository.save(task);
    }

    public Page<TasksDTO> findAll(Pageable pageable) {
        Page<Tasks> tasksPage = tasksRepository.findAllByCompletedTrue(pageable);

        return tasksPage.map(tasksMapper::mapToDto);
    }

    public TasksDTO findById(Long id) {
        Optional<TasksDTO> task = tasksRepository.findById(id).map(tasksMapper::mapToDto);

        return task.orElse(null);
    }

    public Tasks updateTask(Long id, TasksUpdateDTO tasksUpdateDTO) {
        Optional<Tasks> optionalTask = tasksRepository.findById(id);

        if (optionalTask.isPresent()) {
            Tasks task = optionalTask.get();
            task.updateTask(tasksUpdateDTO, developerRepository);

            tasksRepository.save(task);

            return taskUpdateMapper.map(tasksUpdateDTO);
        }

        return null;
    }
}
