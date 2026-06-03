package com.devofthefour.taskboard.history.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.devofthefour.taskboard.database.HistoryDatabase;
import com.devofthefour.taskboard.entity.HistoryChange;

@Repository
public class HistoryChangeRepository {
    private final HistoryDatabase historyDatabase;

    public HistoryChangeRepository(HistoryDatabase historyDatabase) {
        this.historyDatabase = historyDatabase;
    }

    public List<HistoryChange> findAll() {
        return historyDatabase.findAll();
    }

    public Optional<HistoryChange> findById(Long id) {
        return historyDatabase.findById(id);
    }

    public List<HistoryChange> findByTaskId(Long taskId) {
        return historyDatabase.findByTaskId(taskId);
    }

    public HistoryChange save(HistoryChange historyChange) {
        return historyDatabase.save(historyChange);
    }

    public HistoryChange create(HistoryChange historyChange) {
        return historyDatabase.save(historyChange);
    }

    public void deleteById(Long id) {
        historyDatabase.deleteById(id);
    }
}
