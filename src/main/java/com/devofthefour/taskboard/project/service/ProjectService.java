package com.devofthefour.taskboard.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devofthefour.taskboard.entity.Project;
import com.devofthefour.taskboard.project.repository.ProjectRepository;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    public Optional<Project> findByName(String name) {
        return projectRepository.findByName(name);
    }

    public Project create(Project project) {
        if (project.getCreatedAt() == null) {
            project.setCreatedAt(LocalDate.now());
        }
        return projectRepository.create(project);
    }

    public Optional<Project> update(Long id, Project project) {
        return projectRepository.findById(id).map(existing -> {
            if (project.getCreatedAt() == null) {
                project.setCreatedAt(existing.getCreatedAt());
            }
            project.setId(id);
            return projectRepository.save(project);
        });
    }

    public boolean delete(Long id) {
        if (projectRepository.findById(id).isEmpty()) {
            return false;
        }
        projectRepository.deleteById(id);
        return true;
    }
}
