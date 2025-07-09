package com.gmmps.transformer;

import com.gmmps.dto.PaymentDto;
import com.gmmps.entity.Payment;
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
                .build();
    }
}
