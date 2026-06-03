package com.devofthefour.taskboard.history.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devofthefour.taskboard.database.TaskDatabase;
import com.devofthefour.taskboard.entity.HistoryChange;
import com.devofthefour.taskboard.entity.Task;
import com.devofthefour.taskboard.history.controller.HistoryChangeRequest;
import com.devofthefour.taskboard.history.repository.HistoryChangeRepository;

@Service
public class HistoryChangeService {
    private final HistoryChangeRepository historyChangeRepository;
    private final TaskDatabase taskDatabase;

    public HistoryChangeService(HistoryChangeRepository historyChangeRepository, TaskDatabase taskDatabase) {
        this.historyChangeRepository = historyChangeRepository;
        this.taskDatabase = taskDatabase;
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

    public HistoryChange create(HistoryChangeRequest request) {
        HistoryChange historyChange = new HistoryChange();
        applyRequest(historyChange, request);
        if (historyChange.getChangedAt() == null) {
            historyChange.setChangedAt(LocalDate.now());
        }
        return historyChangeRepository.create(historyChange);
    }

    public Optional<HistoryChange> update(Long id, HistoryChangeRequest request) {
        return historyChangeRepository.findById(id).map(existing -> {
            LocalDate changedAt = existing.getChangedAt();
            applyRequest(existing, request);
            if (existing.getChangedAt() == null) {
                existing.setChangedAt(changedAt);
            }
            return historyChangeRepository.save(existing);
        });
    }

    private void applyRequest(HistoryChange historyChange, HistoryChangeRequest request) {
        if (request.getTaskId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "taskId is required");
        }
        Task task = taskDatabase.findById(request.getTaskId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        historyChange.setTask(task);
        historyChange.setFieldName(request.getFieldName());
        historyChange.setOldValue(request.getOldValue());
        historyChange.setNewValue(request.getNewValue());
        historyChange.setChangedAt(request.getChangedAt());
    }

    public boolean delete(Long id) {
        if (historyChangeRepository.findById(id).isEmpty()) {
            return false;
        }
        historyChangeRepository.deleteById(id);
        return true;
    }
}
