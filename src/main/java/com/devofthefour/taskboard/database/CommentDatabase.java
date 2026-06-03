package com.devofthefour.taskboard.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devofthefour.taskboard.entity.Comment;

@Repository
public interface CommentDatabase extends JpaRepository<Comment, Long> {
    List<Comment> findByTaskId(Long taskId);
}