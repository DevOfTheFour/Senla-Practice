package com.devofthefour.taskboard.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devofthefour.taskboard.entity.Project;
import com.devofthefour.taskboard.project.controller.ProjectRequest;
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

    public Project create(ProjectRequest request) {
        Project project = new Project();
        applyRequest(project, request);
        if (project.getCreatedAt() == null) {
            project.setCreatedAt(LocalDate.now());
        }
        return projectRepository.create(project);
    }

    public Optional<Project> update(Long id, ProjectRequest request) {
        return projectRepository.findById(id).map(existing -> {
            LocalDate createdAt = existing.getCreatedAt();
            applyRequest(existing, request);
            if (existing.getCreatedAt() == null) {
                existing.setCreatedAt(createdAt);
            }
            return projectRepository.save(existing);
        });
    }

    private void applyRequest(Project project, ProjectRequest request) {
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setCreatedAt(request.getCreatedAt());
        project.setStatus(request.getStatus());
    }

    public boolean delete(Long id) {
        if (projectRepository.findById(id).isEmpty()) {
            return false;
        }
        projectRepository.deleteById(id);
        return true;
    }
}
