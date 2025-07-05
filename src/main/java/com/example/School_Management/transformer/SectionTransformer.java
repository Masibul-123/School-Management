package com.example.School_Management.transformer;

// SectionTransformer.java

import com.example.School_Management.dto.SchoolClassDto;
import com.example.School_Management.dto.SectionDto;
import com.example.School_Management.entity.SchoolClass;
import com.example.School_Management.entity.Section;
import com.example.School_Management.repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SectionTransformer {


    @Autowired
    private SchoolClassRepository classNameRepository;

    public SectionDto convertEntityToDto(Section section) {
        return SectionDto.builder()
                .id(section.getId())
                .name(section.getName())
                .build();
    }


    public Section convertDtoToEntity(SectionDto sectionDto) {
        Section.SectionBuilder sectionBuilder=Section.builder()
                                    .name(sectionDto.getName());
                    if(sectionDto.getId()!=null)
                        sectionBuilder.id(sectionDto.getId());

                    return sectionBuilder.build();


    }

}