package com.defect.tracker.response.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefectResponse {
    private Long id;
    private String name;
    private String description;
    private String stepToRecreat;
    private String projectName;
    private String priorityName;
    private String seviarityName;
    private String defectTypeName;
    private String defectStatusName;
    private String releaseName;
    private String assigToName;
    private String reportName;
}
