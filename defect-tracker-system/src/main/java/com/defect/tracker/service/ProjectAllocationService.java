package com.defect.tracker.service;

import com.defect.tracker.response.dto.ProjectAllocationResponse;
import com.defect.tracker.resquest.dto.ProjectAllocationRequest;

import java.util.List;

public interface ProjectAllocationService {
    public void saveProjectAllocation(ProjectAllocationRequest projectAllocationRequest);

    public List<ProjectAllocationResponse> getAllProjectAllocation();

    public ProjectAllocationResponse getProjectAllocationById(Long id);

    public boolean existsById(Long id);

    public void deletePRojectAllocation(Long id);
}
