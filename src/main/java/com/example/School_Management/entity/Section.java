package com.example.School_Management.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "section_name",nullable = false)
    private String name;

}
