package com.gmmps.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassInfoDto {

    @Schema(description = "Unique identifier")
    private Long id;
    private String name;
}