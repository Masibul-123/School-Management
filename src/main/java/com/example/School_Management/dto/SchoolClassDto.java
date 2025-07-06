package com.example.School_Management.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolClassDto {

    @Schema(description = "Unique identifier")
    private Long id;
    private String name;

    private SectionDto sectionDto;
}