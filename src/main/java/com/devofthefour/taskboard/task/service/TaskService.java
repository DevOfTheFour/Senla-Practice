package com.devofthefour.taskboard.task.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devofthefour.taskboard.entity.Task;
import com.devofthefour.taskboard.task.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public List<Task> findByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> findByAssigneeId(Long assigneeId) {
        return taskRepository.findByAssigneeId(assigneeId);
    }

    public List<Task> findByAssigneeIsNull() {
        return taskRepository.findByAssigneeIsNull();
    }

    public List<Task> findByDeadlineBefore(LocalDate date) {
        return taskRepository.findByDeadlineBefore(date);
    }

    public Task create(Task task) {
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(LocalDate.now());
        }
        return taskRepository.create(task);
    }

    public Optional<Task> update(Long id, Task task) {
        return taskRepository.findById(id).map(existing -> {
            if (task.getCreatedAt() == null) {
                task.setCreatedAt(existing.getCreatedAt());
            }
            task.setId(id);
            return taskRepository.save(task);
        });
    }

    public boolean delete(Long id) {
        if (taskRepository.findById(id).isEmpty()) {
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }
}
