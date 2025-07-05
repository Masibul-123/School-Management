package com.example.School_Management.transformer;

// AddressTransformer.java

import com.example.School_Management.dto.AddressDto;
import com.example.School_Management.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressTransformer {

    public AddressDto convertEntityToDto(Address address) {

        return AddressDto.builder()
                .id(address.getId())
                .area(address.getArea())
                .postOffice(address.getPostOffice())
                .policeStation(address.getPoliceStation())
                .district(address.getDistrict())
                .state(address.getState())
                .pinCode(address.getPinCode())
                .build();
    }


    public Address convertDtoToEntity(AddressDto addressdto) {
        return Address.builder()
                .area(addressdto.getArea())
                .postOffice(addressdto.getPostOffice())
                .policeStation(addressdto.getPoliceStation())
                .district(addressdto.getDistrict())
                .state(addressdto.getState())
                .pinCode(addressdto.getPinCode())
                .build();
    }

}
