package com.devofthefour.taskboard.comment.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devofthefour.taskboard.comment.controller.CommentRequest;
import com.devofthefour.taskboard.comment.repository.CommentRepository;
import com.devofthefour.taskboard.database.TaskDatabase;
import com.devofthefour.taskboard.database.UserDatabase;
import com.devofthefour.taskboard.entity.Comment;
import com.devofthefour.taskboard.entity.Task;
import com.devofthefour.taskboard.entity.User;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskDatabase taskDatabase;
    private final UserDatabase userDatabase;

    public CommentService(CommentRepository commentRepository, TaskDatabase taskDatabase, UserDatabase userDatabase) {
        this.commentRepository = commentRepository;
        this.taskDatabase = taskDatabase;
        this.userDatabase = userDatabase;
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> findByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId);
    }

    public Comment create(CommentRequest request) {
        Comment comment = new Comment();
        applyRequest(comment, request);
        if (comment.getCreatedAt() == null) {
            comment.setCreatedAt(LocalDate.now());
        }
        return commentRepository.create(comment);
    }

    public Optional<Comment> update(Long id, CommentRequest request) {
        return commentRepository.findById(id).map(existing -> {
            LocalDate createdAt = existing.getCreatedAt();
            applyRequest(existing, request);
            if (existing.getCreatedAt() == null) {
                existing.setCreatedAt(createdAt);
            }
            return commentRepository.save(existing);
        });
    }

    private void applyRequest(Comment comment, CommentRequest request) {
        if (request.getTaskId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "taskId is required");
        }
        if (request.getAuthorId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "authorId is required");
        }
        Task task = taskDatabase.findById(request.getTaskId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        User author = userDatabase.findById(request.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        comment.setTask(task);
        comment.setAuthor(author);
        comment.setText(request.getText());
        comment.setCreatedAt(request.getCreatedAt());
    }

    public boolean delete(Long id) {
        if (commentRepository.findById(id).isEmpty()) {
            return false;
        }
        commentRepository.deleteById(id);
        return true;
    }
}
