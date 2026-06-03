package com.devofthefour.taskboard.task.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devofthefour.taskboard.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);

    List<Task> findByStatus(String status);

    List<Task> findByAssigneeId(Long assigneeId);

    List<Task> findByAssigneeIsNull();

    List<Task> findByDeadlineBefore(LocalDate date);
}