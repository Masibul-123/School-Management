package com.example.School_Management.controller;



import com.example.School_Management.dto.AddressDto;
import com.example.School_Management.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/students/addresses")
    public ResponseEntity<List<AddressDto>> findAll() {
        List<AddressDto> addresses = addressService.findAll();
        if (addresses == null || addresses.isEmpty()) {
            throw new EntityNotFoundException("No addresses found.");
        }
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/students/address/{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable long id) {
        AddressDto addressDto = addressService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No address found with ID: " + id));
        return ResponseEntity.ok(addressDto);
    }


    @PutMapping("/students/address/{id}")
    public ResponseEntity<AddressDto> update(@PathVariable long id, @RequestBody AddressDto addressDto) {
        AddressDto updated = addressService.update(id, addressDto);
        if (updated == null) {
            throw new EntityNotFoundException("No address found with ID: " + id);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/students/address/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        String result = addressService.deleteById(id);
        if (result == null) {
            throw new EntityNotFoundException("No address found with ID: " + id);
        }
        return ResponseEntity.ok(result);
    }
}
