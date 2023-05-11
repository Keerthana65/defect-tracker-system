package com.defect.tracker.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date startDate;
    private  Date endDate;
    private Long contactNumber;
    private String contactPerson;

    @ManyToOne
    @JoinColumn(name = "project_StatusId",nullable = false)
    private ProjectStatus projectStatus;
}
