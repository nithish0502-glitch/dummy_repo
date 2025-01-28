package com.examly.springapp.service.impl;

import com.examly.springapp.model.Project;
import com.examly.springapp.repository.ProjectRepository;
import com.examly.springapp.exception.ProjectNotFoundException;
import com.examly.springapp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectById(int projectId) throws ProjectNotFoundException {
        return projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project with ID " + projectId + " not found"));
    }

    @Override
    public void deleteProjectById(int projectId) throws ProjectNotFoundException {
        Project project = getProjectById(projectId);
        projectRepository.delete(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
