package com.gmmps.transformer;

import com.gmmps.dto.SectionInfoDto;
import com.gmmps.entity.SectionInfo;
import org.springframework.stereotype.Component;

@Component
public class SectionTransformer {

    public SectionInfoDto convertEntityToDto(SectionInfo sectionInfo) {
        return SectionInfoDto.builder()
                .id(sectionInfo.getId())
                .name(sectionInfo.getName())
                .build();
    }
    public SectionInfo convertDtoToEntity(SectionInfoDto sectionInfoDto) {
       return SectionInfo.builder()
               .name(sectionInfoDto.getName())
               .build();
    }

}