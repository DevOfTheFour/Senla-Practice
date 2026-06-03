package com.devofthefour.taskboard.task.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devofthefour.taskboard.database.ProjectDatabase;
import com.devofthefour.taskboard.database.UserDatabase;
import com.devofthefour.taskboard.entity.Project;
import com.devofthefour.taskboard.entity.Task;
import com.devofthefour.taskboard.entity.User;
import com.devofthefour.taskboard.task.controller.TaskRequest;
import com.devofthefour.taskboard.task.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectDatabase projectDatabase;
    private final UserDatabase userDatabase;

    public TaskService(TaskRepository taskRepository, ProjectDatabase projectDatabase, UserDatabase userDatabase) {
        this.taskRepository = taskRepository;
        this.projectDatabase = projectDatabase;
        this.userDatabase = userDatabase;
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

    public Task create(TaskRequest request) {
        Task task = new Task();
        applyRequest(task, request);
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(LocalDate.now());
        }
        return taskRepository.create(task);
    }

    public Optional<Task> update(Long id, TaskRequest request) {
        return taskRepository.findById(id).map(existing -> {
            LocalDate createdAt = existing.getCreatedAt();
            applyRequest(existing, request);
            if (existing.getCreatedAt() == null) {
                existing.setCreatedAt(createdAt);
            }
            return taskRepository.save(existing);
        });
    }

    private void applyRequest(Task task, TaskRequest request) {
        if (request.getProjectId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "projectId is required");
        }
        Project project = projectDatabase.findById(request.getProjectId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
        User assignee = findAssignee(request.getAssigneeId());

        task.setProject(project);
        task.setAssignee(assignee);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        task.setCreatedAt(request.getCreatedAt());
        task.setDeadline(request.getDeadline());
    }

    private User findAssignee(Long assigneeId) {
        if (assigneeId == null) {
            return null;
        }
        return userDatabase.findById(assigneeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignee not found"));
    }

    public boolean delete(Long id) {
        if (taskRepository.findById(id).isEmpty()) {
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }
}
