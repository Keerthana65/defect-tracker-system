package com.defect.tracker.search.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefectSearch {
    private String defectName;
    private String defectDescription;
    private String defectStepToRecreat;
    private String ProjectName;
    private String priorityName;
    private String priorityColor;
    private String seviarityName;
    private String seviarityColor;
    private String defectTypeName;
    private String defectStatusName;
    private String assigntoName;
    private String reportToname;
    private String releaseName;


}
