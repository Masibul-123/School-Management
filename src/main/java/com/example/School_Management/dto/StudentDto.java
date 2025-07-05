package com.example.School_Management.dto;



import lombok.*;

import java.util.Date;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {

    private Long id;
    private long rollNo;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;


    private SchoolClassDto schoolClassDto;     // Refers to ClassName entity
    private ParentDto parentDto;   // Refers to Parent entity
    private AddressDto addressDto;   // Refers to Address entity


}
