package com.devofthefour.taskboard.comment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.devofthefour.taskboard.database.CommentDatabase;
import com.devofthefour.taskboard.entity.Comment;

@Repository
public class CommentRepository {
    private final CommentDatabase commentDatabase;

    public CommentRepository(CommentDatabase commentDatabase) {
        this.commentDatabase = commentDatabase;
    }

    public List<Comment> findAll() {
        return commentDatabase.findAll();
    }

    public Optional<Comment> findById(Long id) {
        return commentDatabase.findById(id);
    }

    public List<Comment> findByTaskId(Long taskId) {
        return commentDatabase.findByTaskId(taskId);
    }

    public Comment save(Comment comment) {
        return commentDatabase.save(comment);
    }

    public Comment create(Comment comment) {
        return commentDatabase.save(comment);
    }

    public void deleteById(Long id) {
        commentDatabase.deleteById(id);
    }
}
