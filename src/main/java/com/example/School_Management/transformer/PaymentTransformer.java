package com.example.School_Management.transformer;


import com.example.School_Management.dto.*;
import com.example.School_Management.entity.*;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class PaymentTransformer {


    // Convert Entity to DTO
    public PaymentDto convertEntityToDto(Payment payment) {

        return PaymentDto.builder()
                .id(payment.getId())
                .tuitionFee(payment.getTuitionFee())
                .transportFee(payment.getTransportFee())
                .examFee(payment.getExamFee())
                .otherFee(payment.getOtherFee())
                .totalFee(payment.getTotalFee())
                .paymentDate(payment.getPaymentDate())
                .paidMonth(payment.getPaidMonth())
                .paidYear(payment.getPaidYear())

                .studentDto(StudentDto.builder()
                        .id(payment.getStudent().getId())
                        .rollNo(payment.getStudent().getRollNo())
                        .firstName(payment.getStudent().getFirstName())
                        .middleName(payment.getStudent().getMiddleName())
                        .lastName(payment.getStudent().getLastName())
                        .dateOfBirth(payment.getStudent().getDateOfBirth())
                        .gender(payment.getStudent().getGender())

                        .schoolClassDto(SchoolClassDto.builder()
                                .id(payment.getStudent().getSchoolClass().getId())
                                .name(payment.getStudent().getSchoolClass().getName())
                                .sectionDto(SectionDto.builder()
                                        .id(payment.getStudent().getSchoolClass().getSection().getId())
                                        .name(payment.getStudent().getSchoolClass().getSection().getName())
                                        .build())
                                .build())
                        .parentDto(ParentDto.builder()
                                .id(payment.getStudent().getParent().getId())
                                .fatherName(payment.getStudent().getParent().getFatherName())
                                .motherName(payment.getStudent().getParent().getMotherName())
                                .firstContactNo(payment.getStudent().getParent().getFirstContactNo())
                                .secondContactNo(payment.getStudent().getParent().getSecondContactNo())
                                .build())

                        .addressDto(AddressDto.builder()
                                .id(payment.getStudent().getAddress().getId())
                                .area(payment.getStudent().getAddress().getArea())
                                .postOffice(payment.getStudent().getAddress().getPostOffice())
                                .policeStation(payment.getStudent().getAddress().getPoliceStation())
                                .district(payment.getStudent().getAddress().getDistrict())
                                .state(payment.getStudent().getAddress().getState())
                                .pinCode(payment.getStudent().getAddress().getPinCode())
                                .build())
                        .build())
                .build();





    }

    // Convert DTO to Entity
    public Payment convertDtoToEntity(PaymentDto paymentDto) {
        return Payment.builder()

                .tuitionFee(paymentDto.getTuitionFee())
                .transportFee(paymentDto.getTransportFee())
                .examFee(paymentDto.getExamFee())
                .otherFee(paymentDto.getOtherFee())
                .totalFee(paymentDto.getTotalFee())
                .paymentDate(new java.sql.Date(paymentDto.getPaymentDate().getTime()))

                .paidMonth(paymentDto.getPaidMonth())
                .paidYear(paymentDto.getPaidYear())

                .student(Student.builder()
                        .id(paymentDto.getStudentDto().getId())
                        .build())
                .build();
    }
}
