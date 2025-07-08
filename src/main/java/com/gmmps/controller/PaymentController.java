package com.gmmps.controller;

import com.gmmps.dto.PaymentDto;
import com.gmmps.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/students/payments")
@Tag(name = "Payment", description = "Find All,Find BY Id,Save,Update ,Delete By Id")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("")
    @Operation(summary = "Get all payments", description = "Returns a list of all payments with student details ")
    public ResponseEntity<List<PaymentDto>> findAll() {
        List<PaymentDto> payments = paymentService.findAll();
        if (payments.isEmpty()) {
            throw new EntityNotFoundException("No payment records found.");
        }
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get payments by id", description = "Returns a payments with student details ")
    public ResponseEntity<PaymentDto> findById(@PathVariable long id) {
        PaymentDto dto = paymentService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No payment found with ID: " + id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("")
    @Operation(summary = "Add payment details", description = "Returns a payment with student details ,student id should be mentioned not payment id")
    public ResponseEntity<PaymentDto> save(@RequestBody PaymentDto dto) {
        PaymentDto saved = paymentService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update payment details", description = "Returns a payment with student details,payment id and student id should be mentioned ")
    public ResponseEntity<PaymentDto> update(@PathVariable long id, @RequestBody PaymentDto dto) {
        PaymentDto updated = paymentService.update(id, dto);
        if (updated == null) {
            throw new EntityNotFoundException("No payment found with ID: " + id);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete payment details", description = "Returns a  payment with student details,payment id should be mentioned ")
    public ResponseEntity<String> delete(@PathVariable long id) {
        String result = paymentService.deleteById(id);
        if (result == null) {
            throw new EntityNotFoundException("No payment found with ID: " + id);
        }
        return ResponseEntity.ok(result);
    }
}
