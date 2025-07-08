package com.gmmps.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentDto {

    private Long id;
    private String fatherName;
    private String motherName;
    private long primaryContactNo;
    private long secondaryContactNo;
    private String email;

}