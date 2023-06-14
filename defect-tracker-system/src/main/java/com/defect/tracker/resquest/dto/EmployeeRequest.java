package com.defect.tracker.resquest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
    private Long id;
    private String firName;
    private String lasName;
    private String email;
    private String address;
    private Long phoneNumber;
    private Long designationId;
}
