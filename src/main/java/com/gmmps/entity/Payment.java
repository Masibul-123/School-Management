package com.gmmps.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Tuition_Fee")
    private Integer tuitionFee;

    @Column(name = "Transport_Fee")
    private Integer transportFee;

    @Column(name = "Exam_Fee")
    private Integer examFee;

    @Column(name = "Other_Fee")
    private Integer otherFee;

    @Column(name = "Total_Fee")
    private Integer totalFee;

    @Column(name = "Payment_Date")
    private Date paymentDate;

    @Column(name = "Paid_Month")
    private Integer paidMonth;

    @Column(name = "Paid_Year")
    private Integer paidYear;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Student_Id")
    private Student student;
}