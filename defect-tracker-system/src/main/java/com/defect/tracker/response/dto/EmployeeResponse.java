package com.defect.tracker.response.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EmployeeResponse {

    private Long id;
    private String firName;
    private String lasName;
    private String email;
    private String address;
    private Long phoneNumber;
    private String designationName;
}
