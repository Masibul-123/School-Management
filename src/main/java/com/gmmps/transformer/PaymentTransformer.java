package com.gmmps.transformer;

import com.gmmps.dto.*;
import com.gmmps.entity.Payment;
import com.gmmps.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class PaymentTransformer {

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

                        .classInfoDto(ClassInfoDto.builder()
                                .id(payment.getStudent().getClassInfo().getId())
                                .className(payment.getStudent().getClassInfo().getName())
                                .sectionDto(SectionDto.builder()
                                        .id(payment.getStudent().getClassInfo().getSection().getId())
                                        .sectionName(payment.getStudent().getClassInfo().getSection().getSectionName())
                                        .build())
                                .build())
                        .parentDto(ParentDto.builder()
                                .id(payment.getStudent().getParent().getId())
                                .fatherName(payment.getStudent().getParent().getFatherName())
                                .motherName(payment.getStudent().getParent().getMotherName())
                                .primaryContactNo(payment.getStudent().getParent().getPrimaryContactNo())
                                .secondaryContactNo(payment.getStudent().getParent().getSecondaryContactNo())
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
