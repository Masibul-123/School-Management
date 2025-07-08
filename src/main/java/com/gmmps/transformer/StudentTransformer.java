package com.gmmps.transformer;

import com.gmmps.dto.*;
import com.gmmps.entity.Address;
import com.gmmps.entity.Parent;
import com.gmmps.entity.Student;
import com.gmmps.repository.AddressRepository;
import com.gmmps.repository.ClassInfoRepository;
import com.gmmps.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class StudentTransformer {

    public StudentDto convertEntityToDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .rollNo(student.getRollNo())
                .firstName(student.getFirstName())
                .middleName(student.getMiddleName())
                .lastName(student.getLastName())
                .dateOfBirth(student.getDateOfBirth())
                .gender(student.getGender())
                .classInfoDto(
                        student.getClassInfo() != null
                                ? ClassInfoDto.builder()
                                .id(student.getClassInfo().getId())
                                .className(student.getClassInfo().getName())
                                .sectionDto(SectionDto.builder()
                                        .id(student.getClassInfo().getSection().getId())
                                        .sectionName(student.getClassInfo().getSection().getSectionName())
                                        .build())
                                .build()
                                : null
                )

                .parentDto(ParentDto.builder()
                        .id(student.getParent().getId())
                        .fatherName(student.getParent().getFatherName())
                        .motherName(student.getParent().getMotherName())
                        .primaryContactNo(student.getParent().getPrimaryContactNo())
                        .secondaryContactNo(student.getParent().getSecondaryContactNo())
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
            .primaryContactNo(studentDto.getParentDto().getPrimaryContactNo())
            .secondaryContactNo(studentDto.getParentDto().getSecondaryContactNo());
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

            .classInfo(null)

            .parent(parentBuilder.build())

            .address(addressBuilder.build());

    if (studentDto.getId() != null)
        studentBuilder.id(studentDto.getId());


    return studentBuilder.build();
}



}



















