package com.example.School_Management.dto;



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
    private long firstContactNo;
    private long secondContactNo;


}