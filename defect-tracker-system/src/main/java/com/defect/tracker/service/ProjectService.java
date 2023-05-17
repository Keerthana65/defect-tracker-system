package com.defect.tracker.service;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.response.dto.ProjectResponse;
import com.defect.tracker.resquest.dto.ProjectRequest;
import com.defect.tracker.search.dto.ProjectSearch;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    public void saveProject(ProjectRequest projectRequest);

    public boolean exisByName(String name);

    public boolean existsByProjectNameAndIdNot(String name,Long id);

    public boolean existsById(Long id);

    public List<ProjectResponse> getAllProject();

    public ProjectResponse getProjectById(Long id);

    public void deleteProject(Long id);

    public List<ProjectResponse> multiSearchProject(Pageable pageable, PaginatedContentResponse.Pagination pagination, ProjectSearch projectSearch);
}
