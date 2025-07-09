package com.gmmps.service;

import com.gmmps.dto.AddressDto;
import com.gmmps.entity.Address;
import com.gmmps.repository.AddressRepository;
import com.gmmps.transformer.AddressTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressTransformer addressTransformer;

    public AddressService(AddressRepository addressRepository, AddressTransformer addressTransformer) {
        this.addressRepository = addressRepository;
        this.addressTransformer = addressTransformer;
    }

    public List<AddressDto> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(addressTransformer::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<AddressDto> getAddressById(long id) {
        return addressRepository.findById(id)
                .map(addressTransformer::convertEntityToDto);
    }

    @Transactional
    public String deleteById(long id) {
        if (!addressRepository.existsById(id))
            return null;
        addressRepository.deleteById(id);
        return "Address with ID " + id + " deleted successfully.";
    }
}
