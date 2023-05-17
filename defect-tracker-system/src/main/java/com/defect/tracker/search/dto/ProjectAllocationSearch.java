package com.defect.tracker.search.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectAllocationSearch {
    private String projectName;
    private String employeeName;
    private String roleName;
}
