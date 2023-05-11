package com.defect.tracker.resquest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectAllocationRequest {
    private Long id;
    private Long projectId;
    private Long employeeId;
    private Long roleId;
}
