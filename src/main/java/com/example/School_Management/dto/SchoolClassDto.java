package com.example.School_Management.dto;



import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolClassDto {

    private Long id;
    private String name;

    private SectionDto sectionDto;
}