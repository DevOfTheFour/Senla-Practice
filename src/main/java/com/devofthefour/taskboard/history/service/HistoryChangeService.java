package com.devofthefour.taskboard.history.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devofthefour.taskboard.entity.HistoryChange;
import com.devofthefour.taskboard.history.repository.HistoryChangeRepository;

@Service
public class HistoryChangeService {
    private final HistoryChangeRepository historyChangeRepository;

    public HistoryChangeService(HistoryChangeRepository historyChangeRepository) {
        this.historyChangeRepository = historyChangeRepository;
    }

    public List<HistoryChange> findAll() {
        return historyChangeRepository.findAll();
    }

    public Optional<HistoryChange> findById(Long id) {
        return historyChangeRepository.findById(id);
    }

    public List<HistoryChange> findByTaskId(Long taskId) {
        return historyChangeRepository.findByTaskId(taskId);
    }

    public HistoryChange create(HistoryChange historyChange) {
        if (historyChange.getChangedAt() == null) {
            historyChange.setChangedAt(LocalDate.now());
        }
        return historyChangeRepository.create(historyChange);
    }

    public Optional<HistoryChange> update(Long id, HistoryChange historyChange) {
        return historyChangeRepository.findById(id).map(existing -> {
            if (historyChange.getChangedAt() == null) {
                historyChange.setChangedAt(existing.getChangedAt());
            }
            historyChange.setId(id);
            return historyChangeRepository.save(historyChange);
        });
    }

    public boolean delete(Long id) {
        if (historyChangeRepository.findById(id).isEmpty()) {
            return false;
        }
        historyChangeRepository.deleteById(id);
        return true;
    }
}
