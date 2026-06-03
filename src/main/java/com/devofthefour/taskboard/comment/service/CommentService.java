package com.devofthefour.taskboard.comment.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devofthefour.taskboard.comment.repository.CommentRepository;
import com.devofthefour.taskboard.entity.Comment;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
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

    public Comment create(Comment comment) {
        if (comment.getCreatedAt() == null) {
            comment.setCreatedAt(LocalDate.now());
        }
        return commentRepository.create(comment);
    }

    public Optional<Comment> update(Long id, Comment comment) {
        return commentRepository.findById(id).map(existing -> {
            if (comment.getCreatedAt() == null) {
                comment.setCreatedAt(existing.getCreatedAt());
            }
            comment.setId(id);
            return commentRepository.save(comment);
        });
    }

    public boolean delete(Long id) {
        if (commentRepository.findById(id).isEmpty()) {
            return false;
        }
        commentRepository.deleteById(id);
        return true;
    }
}
