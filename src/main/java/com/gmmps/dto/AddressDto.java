package com.gmmps.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    private Long id;
    private String area;
    private String postOffice;
    private String policeStation;
    private String district;
    private String state;
    private long pinCode;
}
