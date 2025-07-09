package com.gmmps.transformer;

import com.gmmps.dto.*;
import com.gmmps.entity.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
                .parentDto(ParentDto.builder()
                        .id(student.getParent().getId())
                        .fatherName(student.getParent().getFatherName())
                        .motherName(student.getParent().getMotherName())
                        .primaryContactNo(student.getParent().getPrimaryContactNo())
                        .secondaryContactNo(student.getParent().getSecondaryContactNo())
                        .email(student.getParent().getEmail())
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
                .classInfoDto(
                        student.getClassInfo() != null
                                ? ClassInfoDto.builder()
                                .id(student.getClassInfo().getId())
                                .name(student.getClassInfo().getName())
                                .build()
                                : null)
                .sectionInfoDto(
                        student.getSectionInfo() != null
                                ? SectionInfoDto.builder()
                                .id(student.getSectionInfo().getId())
                                .name(student.getSectionInfo().getName())
                                .build()
                                : null)
                .paymentListDto(
                        student.getPaymentList().isEmpty()
                                ? null
                                : student.getPaymentList().stream()
                                .map(payment -> PaymentDto.builder()
                                        .id(payment.getId())
                                        .tuitionFee(payment.getTuitionFee())
                                        .transportFee(payment.getTransportFee())
                                        .examFee(payment.getExamFee())
                                        .otherFee(payment.getOtherFee())
                                        .totalFee(payment.getTotalFee())
                                        .paymentDate(payment.getPaymentDate())
                                        .paidMonth(payment.getPaidMonth())
                                        .paidYear(payment.getPaidYear())
                                        .build())
                                .toList())
                .build();

    }




public Student convertDtoToEntity(StudentDto studentDto) {

    Parent.ParentBuilder parentBuilder = Parent.builder()
            .fatherName(studentDto.getParentDto().getFatherName())
            .motherName(studentDto.getParentDto().getMotherName())
            .primaryContactNo(studentDto.getParentDto().getPrimaryContactNo())
            .secondaryContactNo(studentDto.getParentDto().getSecondaryContactNo())
            .email(studentDto.getParentDto().getEmail());

    Address.AddressBuilder addressBuilder = Address.builder()
            .area(studentDto.getAddressDto().getArea())
            .postOffice(studentDto.getAddressDto().getPostOffice())
            .policeStation(studentDto.getAddressDto().getPoliceStation())
            .district(studentDto.getAddressDto().getDistrict())
            .state(studentDto.getAddressDto().getState())
            .pinCode(studentDto.getAddressDto().getPinCode());

    List<Payment> payments = (studentDto.getPaymentListDto() == null || studentDto.getPaymentListDto().isEmpty())
            ? new ArrayList<>()
            : studentDto.getPaymentListDto().stream()
            .map(paymentDto -> {
                Payment.PaymentBuilder paymentBuilder = Payment.builder()
                        .tuitionFee(paymentDto.getTuitionFee())
                        .transportFee(paymentDto.getTransportFee())
                        .examFee(paymentDto.getExamFee())
                        .otherFee(paymentDto.getOtherFee())
                        .totalFee(paymentDto.getTotalFee())
                        .paymentDate(new java.sql.Date(paymentDto.getPaymentDate().getTime()))
                        .paidMonth(paymentDto.getPaidMonth())
                        .paidYear(paymentDto.getPaidYear());
                if (paymentDto.getId() != 0)
                    paymentBuilder.id(paymentDto.getId());
                return paymentBuilder.build();
            })
            .toList();

    Student.StudentBuilder studentBuilder = Student.builder()
            .rollNo(studentDto.getRollNo())
            .firstName(studentDto.getFirstName())
            .middleName(studentDto.getMiddleName())
            .lastName(studentDto.getLastName())
            .dateOfBirth(new Date(studentDto.getDateOfBirth().getTime()))
            .gender(studentDto.getGender())
            .parent(parentBuilder.build())
            .address(addressBuilder.build())
            .classInfo(null)
            .sectionInfo(null)
            .paymentList(payments);

    return studentBuilder.build();
    }

}



















