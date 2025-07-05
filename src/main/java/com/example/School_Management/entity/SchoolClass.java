package com.example.School_Management.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "School_Class")
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;


}