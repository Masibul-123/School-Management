package com.gmmps.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Area")
    private String area;

    @Column(name = "Post_Office")
    private String postOffice;

    @Column(name = "Police_Station")
    private String policeStation;

    @Column(name = "District")
    private String district;

    @Column(name = "State")
    private String state;

    @Column(name = "Pin_Code")
    private long pinCode;

}