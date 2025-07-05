package com.example.School_Management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Parents_details")
public class Parent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Father_name")
    private String fatherName;

    @Column(name = "Mother_name")
    private String motherName;

    @Column(name = "First_Contact_No")
    private long firstContactNo;

    @Column(name = "Second_Contact_No")
    private long secondContactNo;

}
