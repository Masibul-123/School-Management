package com.example.School_Management.service;

import com.example.School_Management.dto.PaymentDto;
import com.example.School_Management.entity.Payment;
import com.example.School_Management.entity.Student;
import com.example.School_Management.repository.PaymentRepository;
import com.example.School_Management.repository.StudentRepository;
import com.example.School_Management.transformer.PaymentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public PaymentDto save(PaymentDto dto) {

        Student student=studentRepository.findById(dto.getStudentDto().getId())
                .orElse(null);

        Payment payment= paymentTransformer.convertDtoToEntity(dto);
        payment.setStudent(student);

       return paymentTransformer.convertEntityToDto(paymentRepository.save(payment));

    }

    public PaymentDto update(long id, PaymentDto dto) {
        if (!paymentRepository.existsById(id)) {
            return null;
        }

        Payment payment = paymentTransformer.convertDtoToEntity(dto);
        payment.setId(id);
        Payment updated = paymentRepository.save(payment);
        return paymentTransformer.convertEntityToDto(updated);
    }

    public String deleteById(long id) {
        if (!paymentRepository.existsById(id)) {
            return null;
        }
        paymentRepository.deleteById(id);
        return "Payment with ID " + id;
    }
}
