package com.devofthefour.taskboard.history.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devofthefour.taskboard.entity.HistoryChange;

public interface HistoryChangeRepository extends JpaRepository<HistoryChange, Long> {
    List<HistoryChange> findByTaskId(Long taskId);
}