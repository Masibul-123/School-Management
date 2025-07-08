package com.gmmps.transformer;

import com.gmmps.dto.ParentDto;
import com.gmmps.entity.Parent;
import org.springframework.stereotype.Component;

@Component
public class ParentTransformer {

    public ParentDto convertEntityToDto(Parent parent) {
        return ParentDto.builder()
                .id(parent.getId())
                .fatherName(parent.getFatherName())
                .motherName(parent.getMotherName())
                .primaryContactNo(parent.getPrimaryContactNo())
                .secondaryContactNo(parent.getSecondaryContactNo())
                .build();
    }

    public Parent convertDtoToEntity(ParentDto parentDto) {
        return Parent.builder()
                .fatherName(parentDto.getFatherName())
                .motherName(parentDto.getMotherName())
                .primaryContactNo(parentDto.getPrimaryContactNo())
                .secondaryContactNo(parentDto.getSecondaryContactNo())
                .build();
    }
}
