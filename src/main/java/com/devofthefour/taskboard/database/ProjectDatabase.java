package com.devofthefour.taskboard.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devofthefour.taskboard.entity.Project;

@Repository
public interface ProjectDatabase extends JpaRepository<Project, Long> {
    Optional<Project> findByName(String name);
}
