package com.devofthefour.taskboard.task.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.devofthefour.taskboard.database.TaskDatabase;
import com.devofthefour.taskboard.entity.Task;

@Repository
public class TaskRepository {
    private final TaskDatabase taskDatabase;

    public TaskRepository(TaskDatabase taskDatabase) {
        this.taskDatabase = taskDatabase;
    }

    public List<Task> findAll() {
        return taskDatabase.findAll();
    }

    public Optional<Task> findById(Long id) {
        return taskDatabase.findById(id);
    }

    public List<Task> findByProjectId(Long projectId) {
        return taskDatabase.findByProjectId(projectId);
    }

    public List<Task> findByStatus(String status) {
        return taskDatabase.findByStatus(status);
    }

    public List<Task> findByAssigneeId(Long assigneeId) {
        return taskDatabase.findByAssigneeId(assigneeId);
    }

    public List<Task> findByAssigneeIsNull() {
        return taskDatabase.findByAssigneeIsNull();
    }

    public List<Task> findByDeadlineBefore(LocalDate date) {
        return taskDatabase.findByDeadlineBefore(date);
    }

    public Task save(Task task) {
        return taskDatabase.save(task);
    }

    public Task create(Task task) {
        return taskDatabase.save(task);
    }

    public void deleteById(Long id) {
        taskDatabase.deleteById(id);
    }
}
