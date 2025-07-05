package com.example.School_Management.transformer;


import com.example.School_Management.dto.*;
import com.example.School_Management.entity.*;
import com.example.School_Management.repository.AddressRepository;
import com.example.School_Management.repository.SchoolClassRepository;
import com.example.School_Management.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class StudentTransformer {


    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private SchoolClassRepository classNameRepository;
    // @Autowired
    //private PaymentRepository paymentRepository;

    public StudentDto convertEntityToDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .rollNo(student.getRollNo())
                .firstName(student.getFirstName())
                .middleName(student.getMiddleName())
                .lastName(student.getLastName())
                .dateOfBirth(student.getDateOfBirth())
                .gender(student.getGender())
                .schoolClassDto(
                        student.getSchoolClass() != null
                                ? SchoolClassDto.builder()
                                .id(student.getSchoolClass().getId())
                                .name(student.getSchoolClass().getName())
                                .sectionDto(SectionDto.builder()
                                        .id(student.getSchoolClass().getSection().getId())
                                        .name(student.getSchoolClass().getSection().getName())
                                        .build())
                                .build()
                                : null
                )

                .parentDto(ParentDto.builder()
                        .id(student.getParent().getId())
                        .fatherName(student.getParent().getFatherName())
                        .motherName(student.getParent().getMotherName())
                        .firstContactNo(student.getParent().getFirstContactNo())
                        .secondContactNo(student.getParent().getSecondContactNo())
                        .build())

                .addressDto(AddressDto.builder()
                        .id(student.getAddress().getId())
                        .area(student.getAddress().getArea())
                        .postOffice(student.getAddress().getPostOffice())
                        .policeStation(student.getAddress().getPoliceStation())
                        .district(student.getAddress().getDistrict())
                        .state(student.getAddress().getState())
                        .pinCode(student.getAddress().getPinCode())
                        .build())
                .build();

    }




public Student convertDtoToEntity(StudentDto studentDto) {




    Parent.ParentBuilder parentBuilder = Parent.builder()
            .fatherName(studentDto.getParentDto().getFatherName())
            .motherName(studentDto.getParentDto().getMotherName())
            .firstContactNo(studentDto.getParentDto().getFirstContactNo())
            .secondContactNo(studentDto.getParentDto().getSecondContactNo());
    if (studentDto.getParentDto().getId() != null)
        parentBuilder.id(studentDto.getParentDto().getId());


    Address.AddressBuilder addressBuilder = Address.builder()
            .area(studentDto.getAddressDto().getArea())
            .postOffice(studentDto.getAddressDto().getPostOffice())
            .policeStation(studentDto.getAddressDto().getPoliceStation())
            .district(studentDto.getAddressDto().getDistrict())
            .state(studentDto.getAddressDto().getState())
            .pinCode(studentDto.getAddressDto().getPinCode());
    if (studentDto.getAddressDto().getId() != null)
        addressBuilder.id(studentDto.getAddressDto().getId());


    Student.StudentBuilder studentBuilder = Student.builder()

            .rollNo(studentDto.getRollNo())
            .firstName(studentDto.getFirstName())
            .middleName(studentDto.getMiddleName())
            .lastName(studentDto.getLastName())
            .dateOfBirth(new Date(studentDto.getDateOfBirth().getTime()))
            .gender(studentDto.getGender())

            .schoolClass(null)

            .parent(parentBuilder.build())

            .address(addressBuilder.build());

    if (studentDto.getId() != null)
        studentBuilder.id(studentDto.getId());


    return studentBuilder.build();
}



}



















