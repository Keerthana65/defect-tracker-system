package com.defect.tracker.resquest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefectRequest {
    private Long id;
    private String name;
    private String description;
    private String stepToRecreat;
    private Long projectId;
    private Long priorityId;
    private Long seviarityId;
    private Long defectTypeId;
    private Long defectStatusId;
    private Long releaseId;
    private Long assigToId;
    private Long reportId;
}
