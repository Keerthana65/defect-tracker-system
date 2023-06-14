package com.defect.tracker.search.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjectSearch {
    private String name;
    private String contactNumber;
    private String contactPerson;
    private String projectStatusName;
}
