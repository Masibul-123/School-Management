package com.example.School_Management.transformer;

// ParentTransformer.java

import com.example.School_Management.dto.ParentDto;
import com.example.School_Management.entity.Parent;
import org.springframework.stereotype.Component;

@Component
public class ParentTransformer {

    // Convert Parent Entity to ParentDto
    public ParentDto convertEntityToDto(Parent parent) {
        return ParentDto.builder()
                .id(parent.getId())
                .fatherName(parent.getFatherName())
                .motherName(parent.getMotherName())
                .firstContactNo(parent.getFirstContactNo())
                .secondContactNo(parent.getSecondContactNo())
                .build();
    }

    // Convert ParentDto to Parent Entity
    public Parent convertDtoToEntity(ParentDto parentDto) {

        return Parent.builder()
                .fatherName(parentDto.getFatherName())
                .motherName(parentDto.getMotherName())
                .firstContactNo(parentDto.getFirstContactNo())
                .secondContactNo(parentDto.getSecondContactNo())
                .build();
    }
}
