package com.defect.tracker.resquest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjectRequest {
    private Long id;
    private String name;
    private Date startDate;
    private  Date endDate;
    private Long contactNumber;
    private String contactPerson;
    private Long projectStatusId;
}
