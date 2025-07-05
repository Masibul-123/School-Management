package com.example.School_Management.controller;

import com.example.School_Management.dto.PaymentDto;
import com.example.School_Management.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/students/payments")
    public ResponseEntity<List<PaymentDto>> findAll() {
        List<PaymentDto> payments = paymentService.findAll();
        if (payments.isEmpty()) {
            throw new EntityNotFoundException("No payment records found.");
        }
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/students/payments/{id}")
    public ResponseEntity<PaymentDto> findById(@PathVariable long id) {
        PaymentDto dto = paymentService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No payment found with ID: " + id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/students/payment")
    public ResponseEntity<PaymentDto> save(@RequestBody PaymentDto dto) {
        PaymentDto saved = paymentService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/students/payments/{id}")
    public ResponseEntity<PaymentDto> update(@PathVariable long id, @RequestBody PaymentDto dto) {
        PaymentDto updated = paymentService.update(id, dto);
        if (updated == null) {
            throw new EntityNotFoundException("No payment found with ID: " + id);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/students/payments/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        String result = paymentService.deleteById(id);
        if (result == null) {
            throw new EntityNotFoundException("No payment found with ID: " + id);
        }
        return ResponseEntity.ok(result);
    }
}
