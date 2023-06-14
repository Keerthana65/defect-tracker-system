package com.defect.tracker.service;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.response.dto.DesignationResponse;
import com.defect.tracker.response.dto.ProjectStatusResponse;
import com.defect.tracker.resquest.dto.ProjectStatusRequest;
import com.defect.tracker.search.dto.DesiginationSearch;
import com.defect.tracker.search.dto.ProjectStatusSearch;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectStatusService {
    public void saveProjectStatus(ProjectStatusRequest projectStatusRequest);

    public List<ProjectStatusResponse> getAllProjectStatus();

    public boolean isProjectStatusExists(String name);

    public ProjectStatusResponse getProjectStatusById(Long id);

    public boolean existByProjectStatus(Long id);

    public boolean isUpdatedProjectStatusNameExist(Long id, String name);

    public void deleteProjectStatus(Long id);

    public List<ProjectStatusResponse> multiSearchProjectStatusSearch(Pageable pageable, PaginatedContentResponse.Pagination pagination, ProjectStatusSearch projectStatusSearch);
}
