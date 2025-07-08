package com.gmmps.service;

import com.gmmps.dto.AddressDto;
import com.gmmps.entity.Address;
import com.gmmps.repository.AddressRepository;
import com.gmmps.transformer.AddressTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressTransformer addressTransformer;

    public List<AddressDto> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(addressTransformer::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<AddressDto> findById(long id) {
        return addressRepository.findById(id)
                .map(addressTransformer::convertEntityToDto);
    }
    @Transactional
    public AddressDto save(AddressDto dto) {
        Address address = addressTransformer.convertDtoToEntity(dto);
        Address saved = addressRepository.save(address);
        return addressTransformer.convertEntityToDto(saved);
    }
    @Transactional
    public AddressDto update(long id, AddressDto dto) {
        Optional<Address> existing = addressRepository.findById(id);
        if (existing.isEmpty()) {
            return null;
        }

        Address updated = addressTransformer.convertDtoToEntity(dto);
        updated.setId(id); // ensure correct ID
        Address saved = addressRepository.save(updated);
        return addressTransformer.convertEntityToDto(saved);
    }
    @Transactional
    public String deleteById(long id) {
        if (!addressRepository.existsById(id)) {
            return null;
        }
        addressRepository.deleteById(id);
        return "Address with ID " + id + " deleted successfully.";
    }
}
