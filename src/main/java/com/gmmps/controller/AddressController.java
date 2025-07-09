package com.gmmps.controller;

import com.gmmps.dto.AddressDto;
import com.gmmps.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/addresses")
public class AddressController {

    private final  AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("")
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<AddressDto> addresses = addressService.getAllAddresses();
        if (addresses == null || addresses.isEmpty()) {
            throw new EntityNotFoundException("No addresses found.");
        }
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable long id) {
        AddressDto addressDto = addressService.getAddressById(id)
                .orElseThrow(() -> new EntityNotFoundException("No address found with ID: " + id));
        return ResponseEntity.ok(addressDto);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        String result = addressService.deleteById(id);
        if (result == null) {
            throw new EntityNotFoundException("No address found with ID: " + id);
        }
        return ResponseEntity.ok(result);
    }
}
