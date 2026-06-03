package com.devofthefour.taskboard.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devofthefour.taskboard.entity.HistoryChange;

@Repository
public interface HistoryDatabase extends JpaRepository<HistoryChange, Long> {
    List<HistoryChange> findByTaskId(Long taskId);
}