package com.gmmps.controller;

import com.gmmps.dto.PaymentDto;
import com.gmmps.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/payments")
public class PaymentController {


    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping("")
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        List<PaymentDto> payments = paymentService.getAllPayments();
        if (payments.isEmpty())
            throw new EntityNotFoundException("No payment records found.");
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable long id) {
        PaymentDto dto = paymentService.getPaymentById(id)
                .orElseThrow(() -> new EntityNotFoundException("No payment found with ID: " + id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/payment/{studentId}")
    public ResponseEntity<PaymentDto> addNewPaymentAndLinkWithStudent(@PathVariable Long  studentId,@RequestBody PaymentDto paymentDto) {
        PaymentDto savedPayment = paymentService.addNewPaymentAndLinkWithStudent(studentId,paymentDto);
        if(savedPayment ==null)
            throw new EntityNotFoundException("No Student found with this ID: " + studentId);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    @PutMapping("/payment/{paymentId}")
    public ResponseEntity<PaymentDto> updatePaymentDetails(@PathVariable long paymentId, @RequestBody PaymentDto paymentDto) {
        PaymentDto updated = paymentService.updatePaymentDetails(paymentId, paymentDto);
        if (updated == null) {
            throw new EntityNotFoundException("No payment found with ID: " + paymentId);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaymentDetails(@PathVariable long id) {
        String result = paymentService.deletePaymentDetails(id);
        if (result == null) {
            throw new EntityNotFoundException("No payment found with ID: " + id);
        }
        return ResponseEntity.ok(result);
    }
}
