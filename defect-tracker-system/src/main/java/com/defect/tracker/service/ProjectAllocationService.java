package com.defect.tracker.service;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.response.dto.ProjectAllocationResponse;
import com.defect.tracker.resquest.dto.ProjectAllocationRequest;
import com.defect.tracker.search.dto.ProjectAllocationSearch;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectAllocationService {
    public void saveProjectAllocation(ProjectAllocationRequest projectAllocationRequest);

    public List<ProjectAllocationResponse> getAllProjectAllocation();

    public ProjectAllocationResponse getProjectAllocationById(Long id);

    public boolean existsById(Long id);

    public void deletePRojectAllocation(Long id);

    public List<ProjectAllocationResponse> multiSearchProjectAllocation(Pageable pageable, PaginatedContentResponse.Pagination pagination,
                                                                        ProjectAllocationSearch projectAllocationSearch);
}
