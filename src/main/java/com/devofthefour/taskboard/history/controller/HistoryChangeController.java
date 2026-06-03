package com.devofthefour.taskboard.history.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devofthefour.taskboard.entity.HistoryChange;
import com.devofthefour.taskboard.history.service.HistoryChangeService;

@RestController
@RequestMapping("/api/history-changes")
public class HistoryChangeController {
    private final HistoryChangeService historyChangeService;

    public HistoryChangeController(HistoryChangeService historyChangeService) {
        this.historyChangeService = historyChangeService;
    }

    @GetMapping
    public List<HistoryChange> findAll() {
        return historyChangeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoryChange> findById(@PathVariable Long id) {
        return historyChangeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HistoryChange> create(@RequestBody HistoryChange historyChange) {
        return ResponseEntity.status(HttpStatus.CREATED).body(historyChangeService.create(historyChange));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoryChange> update(@PathVariable Long id, @RequestBody HistoryChange historyChange) {
        return historyChangeService.update(id, historyChange)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return historyChangeService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
