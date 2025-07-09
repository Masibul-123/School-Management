package com.gmmps.dto;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {

    private long id;
    private Integer tuitionFee;
    private Integer transportFee;
    private Integer examFee;
    private Integer otherFee;
    private Integer totalFee;
    private Date paymentDate;
    private Integer paidMonth;
    private Integer paidYear;
}