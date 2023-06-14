package com.defect.tracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Defect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String stepToRecreat;
    @ManyToOne
    @JoinColumn(name = "project_id",nullable = false)
    private Project project;
    @ManyToOne
    @JoinColumn(name = "priority_id",nullable = false)
    private Priority priority;
    @ManyToOne
    @JoinColumn(name = "seviarity_id",nullable = false)
    private Severity seviarity;
    @ManyToOne
    @JoinColumn(name = "defect_type_id",nullable = false)
    private DefectType defectType;
    @ManyToOne
    @JoinColumn(name = "defect_status_id",nullable = false)
    private DefectStatus defectStatus;
    @ManyToOne
    @JoinColumn(name = "assign_to_id",nullable = false)
    private Employee assignTo;
    @ManyToOne
    @JoinColumn(name = "reporter_id",nullable = false)
    private Employee reporter;
    @ManyToOne
    @JoinColumn(name="release_id",nullable = false)
    private Release release;

}
