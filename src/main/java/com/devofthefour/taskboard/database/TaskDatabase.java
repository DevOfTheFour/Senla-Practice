package com.devofthefour.taskboard.database;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devofthefour.taskboard.entity.Task;

@Repository
public interface TaskDatabase extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);

    List<Task> findByStatus(String status);

    List<Task> findByAssigneeId(Long assigneeId);

    List<Task> findByAssigneeIsNull();

    List<Task> findByDeadlineBefore(LocalDate date);
}