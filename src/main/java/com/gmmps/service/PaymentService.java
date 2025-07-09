package com.gmmps.service;

import com.gmmps.dto.PaymentDto;
import com.gmmps.entity.Payment;
import com.gmmps.entity.Student;
import com.gmmps.repository.PaymentRepository;
import com.gmmps.repository.StudentRepository;
import com.gmmps.transformer.PaymentTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final PaymentTransformer paymentTransformer;

    public PaymentService(PaymentRepository paymentRepository, StudentRepository studentRepository, PaymentTransformer paymentTransformer) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
        this.paymentTransformer = paymentTransformer;
    }

    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentTransformer::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<PaymentDto> getPaymentById(long id) {
        return paymentRepository.findById(id)
                .map(paymentTransformer::convertEntityToDto);
    }

    @Transactional
    public PaymentDto addNewPaymentAndLinkWithStudent(Long studentId, PaymentDto paymentDto) {
        if (!studentRepository.existsById(studentId))
            return null;
        Student student = studentRepository.findById(studentId).get();
        Payment payment = paymentTransformer.convertDtoToEntity(paymentDto);
        if (student.getPaymentList() == null) {
            student.setPaymentList(new ArrayList<>());
        }
        Payment savedPayment = paymentRepository.save(payment);
        student.getPaymentList().add(savedPayment);
        return paymentTransformer.convertEntityToDto(savedPayment);
    }

    @Transactional
    public PaymentDto updatePaymentDetails(long paymentId, PaymentDto paymentDto) {
        if (!paymentRepository.existsById(paymentId)) {
            return null;
        }
        Payment updatedPayment = paymentTransformer.convertDtoToEntity(paymentDto);
        updatedPayment.setId(paymentId);
        Payment  returnedPayment= paymentRepository.save(updatedPayment);
        return paymentTransformer.convertEntityToDto(returnedPayment);
    }
    @Transactional
    public String deletePaymentDetails(long id) {
        if (!paymentRepository.existsById(id)) {
            return null;
        }
        paymentRepository.deleteById(id);
        return "Payment with ID " + id;
    }
}
