package com.gmmps.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Parents_Details")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Father_Name")
    private String fatherName;

    @Column(name = "Mother_Name")
    private String motherName;

    @Column(name = "Primary_Contact_No")
    private long primaryContactNo;

    @Column(name = "Secondary_Contact_No")
    private long secondaryContactNo;

    @Column(name = "Email_Id")
    private String email;


}
