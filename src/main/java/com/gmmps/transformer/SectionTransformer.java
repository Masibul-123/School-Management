package com.gmmps.transformer;

// SectionTransformer.java

import com.gmmps.dto.SectionDto;
import com.gmmps.entity.Section;
import com.gmmps.repository.ClassInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SectionTransformer {


    @Autowired
    private ClassInfoRepository classNameRepository;

    public SectionDto convertEntityToDto(Section section) {
        return SectionDto.builder()
                .id(section.getId())
                .sectionName(section.getSectionName())
                .build();
    }

    public Section convertDtoToEntity(SectionDto sectionDto) {
        Section.SectionBuilder sectionBuilder=Section.builder().sectionName(sectionDto.getSectionName());
        if(sectionDto.getId()!=null) {
            sectionBuilder.id(sectionDto.getId());
        }
        return sectionBuilder.build();
    }

}