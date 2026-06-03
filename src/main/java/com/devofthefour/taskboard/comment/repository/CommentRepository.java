package com.devofthefour.taskboard.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devofthefour.taskboard.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTaskId(Long taskId);
}