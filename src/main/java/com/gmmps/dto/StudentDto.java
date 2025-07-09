package com.gmmps.dto;



import lombok.*;
import java.util.Date;
import java.util.List;

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
    private ParentDto parentDto;
    private AddressDto addressDto;
    private ClassInfoDto classInfoDto;
    private SectionInfoDto sectionInfoDto;
    private List<PaymentDto> paymentListDto;

}
