package com.defect.tracker.response.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectAllocationResponse {
    private Long id;
    private String projectName;
    private String employeeName;
    private String roleName;
}
