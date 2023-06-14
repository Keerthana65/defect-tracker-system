package com.defect.tracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firName;
    private String lasName;
    private String email;
    private String address;
    private Long phoneNumber;
    @ManyToOne
    @JoinColumn(name = "designation_id",nullable = false)
    private Designation designation;

}
