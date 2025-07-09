package com.gmmps.transformer;

import com.gmmps.dto.ClassInfoDto;
import com.gmmps.entity.ClassInfo;
import org.springframework.stereotype.Component;

@Component
public class ClassInfoTransformer {
    public ClassInfoDto convertEntityToDto(ClassInfo classInfo) {
        return ClassInfoDto.builder()
                .id(classInfo.getId())
                .name(classInfo.getName())
                .build();
    }
    public ClassInfo convertDtoToEntity(ClassInfoDto classInfoDto) {
            return ClassInfo.builder()
                    .name(classInfoDto.getName())
                    .build();
    }

}
