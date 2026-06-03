package com.devofthefour.taskboard.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devofthefour.taskboard.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByName(String name);
}