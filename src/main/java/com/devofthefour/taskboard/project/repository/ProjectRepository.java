package com.devofthefour.taskboard.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.devofthefour.taskboard.database.ProjectDatabase;
import com.devofthefour.taskboard.entity.Project;

@Repository
public class ProjectRepository {
    private final ProjectDatabase projectDatabase;

    public ProjectRepository(ProjectDatabase projectDatabase) {
        this.projectDatabase = projectDatabase;
    }

    public List<Project> findAll() {
        return projectDatabase.findAll();
    }

    public Optional<Project> findById(Long id) {
        return projectDatabase.findById(id);
    }

    public Optional<Project> findByName(String name) {
        return projectDatabase.findByName(name);
    }

    public Project save(Project project) {
        return projectDatabase.save(project);
    }

    public Project create(Project project) {
        return projectDatabase.save(project);
    }

    public void deleteById(Long id) {
        projectDatabase.deleteById(id);
    }
}
