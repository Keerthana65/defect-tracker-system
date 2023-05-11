package com.defect.tracker.service;

import com.defect.tracker.response.dto.ProjectResponse;
import com.defect.tracker.resquest.dto.ProjectRequest;

import java.util.List;

public interface ProjectService {
    public void saveProject(ProjectRequest projectRequest);

    public boolean exisByName(String name);

    public boolean existsByProjectNameAndIdNot(String name,Long id);

    public boolean existsById(Long id);

    public List<ProjectResponse> getAllProject();

    public ProjectResponse getProjectById(Long id);

    public void deleteProject(Long id);
}
