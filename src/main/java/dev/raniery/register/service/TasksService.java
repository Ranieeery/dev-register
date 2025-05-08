package dev.raniery.register.service;

import dev.raniery.register.exceptions.DeveloperNotFoundException;
import dev.raniery.register.model.tasks.*;
import dev.raniery.register.repository.DeveloperRepository;
import dev.raniery.register.repository.TasksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;
    private final TasksMapper tasksMapper;
    private final TasksRegisterMapper tasksRegisterMapper;
    private final DeveloperRepository developerRepository;

    public TasksService(TasksRepository tasksRepository, TasksMapper tasksMapper,
                        TasksRegisterMapper tasksRegisterMapper, DeveloperRepository developerRepository) {
        this.tasksRepository = tasksRepository;
        this.tasksMapper = tasksMapper;
        this.tasksRegisterMapper = tasksRegisterMapper;
        this.developerRepository = developerRepository;
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
        Optional<TasksDTO> task = tasksRepository.findByIdAndActiveTrue(id).map(tasksMapper::mapToDto);

        return task.orElse(null);
    }

    public TasksDTO findDeletedById(Long id) {
        Optional<TasksDTO> developer = tasksRepository.findByIdAndActiveFalse(id).map(tasksMapper::mapToDto);

        return developer.orElse(null);
    }

    public TasksDTO updateTask(Long id, TasksUpdateDTO tasksUpdateDTO) {
        Optional<Tasks> optionalTask = tasksRepository.findById(id);

        if (optionalTask.isPresent()) {
            Tasks existingTask = optionalTask.get();
            UUID developerId = optionalTask.get().getDeveloper().getId();

            if (developerRepository.existsByIdAndActiveTrue(developerId)) {
                System.out.println(developerId);

                System.out.printf("Developer ID: %s\n", developerId);
                existingTask.updateTask(tasksUpdateDTO, developerRepository);

                Tasks updatedTask = tasksRepository.save(existingTask);

                return tasksMapper.mapToDto(updatedTask);
            } else {
                throw DeveloperNotFoundException.forDeveloperId(developerId);
            }

        }

        return null;
    }

    public void toggleActiveTask(Long id) {
        Tasks task = tasksRepository.getReferenceById(id);

        task.setActive();
        tasksRepository.save(task);
    }
}
