package com.gmmps.service;

import com.gmmps.dto.PaymentDto;
import com.gmmps.entity.Payment;
import com.gmmps.entity.Student;
import com.gmmps.repository.PaymentRepository;
import com.gmmps.repository.StudentRepository;
import com.gmmps.transformer.PaymentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PaymentTransformer paymentTransformer;

    public List<PaymentDto> findAll() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentTransformer::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<PaymentDto> findById(long id) {
        return paymentRepository.findById(id)
                .map(paymentTransformer::convertEntityToDto);
    }

    @Transactional
    public PaymentDto save(PaymentDto dto) {

        Student student=studentRepository.findById(dto.getStudentDto().getId())
                .orElse(null);

        Payment payment= paymentTransformer.convertDtoToEntity(dto);
        payment.setStudent(student);

       return paymentTransformer.convertEntityToDto(paymentRepository.save(payment));

    }
    @Transactional
    public PaymentDto update(long id, PaymentDto dto) {
        if (!paymentRepository.existsById(id)) {
            return null;
        }

        Payment payment = paymentTransformer.convertDtoToEntity(dto);
        payment.setId(id);
        Payment updated = paymentRepository.save(payment);
        return paymentTransformer.convertEntityToDto(updated);
    }
    @Transactional
    public String deleteById(long id) {
        if (!paymentRepository.existsById(id)) {
            return null;
        }
        paymentRepository.deleteById(id);
        return "Payment with ID " + id;
    }
}
