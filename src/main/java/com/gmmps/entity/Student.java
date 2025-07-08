package com.gmmps.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Builder
@Table(name = "STUDENTS",
uniqueConstraints = @UniqueConstraint(columnNames = {"Roll_No", "class_info_id"}))
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Roll_No",nullable = false)
    private long rollNo;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Middle_Name")
    private String middleName;

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "DOB",nullable = false)
    private Date dateOfBirth;

    @Column(name = "Gender",nullable = false)
    private String gender;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "Parent_Id")
    private Parent parent;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "Address_Id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "class_info_id")
    private ClassInfo classInfo;

}