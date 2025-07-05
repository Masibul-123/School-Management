package com.example.School_Management.transformer;

// ClassNameTransformer.java

import com.example.School_Management.dto.SchoolClassDto;
import com.example.School_Management.dto.SectionDto;
import com.example.School_Management.entity.SchoolClass;
import com.example.School_Management.entity.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchoolClassTransformer {




    public SchoolClassDto convertEntityToDto(SchoolClass schoolClass) {
        return SchoolClassDto.builder()
                .id(schoolClass.getId())
                .name(schoolClass.getName())
                .sectionDto(

                                SectionDto.builder()
                                        .id(schoolClass.getSection().getId())
                                        .name(schoolClass.getSection().getName())
                                        .build()
                )
                .build();
    }

    public  SchoolClass convertDtoToEntity(SchoolClassDto schoolClassDto) {
        Section.SectionBuilder sectionBuilder = Section.builder()
                .name(schoolClassDto.getSectionDto().getName());
        if (schoolClassDto.getSectionDto().getId() != null)
            sectionBuilder.id(schoolClassDto.getSectionDto().getId());

        SchoolClass.SchoolClassBuilder schoolClassBuilder = SchoolClass.builder()
                .name(schoolClassDto.getName())
                .section(sectionBuilder.build());
        if (schoolClassDto.getId() != null)
            schoolClassBuilder.id(schoolClassDto.getId());


        return schoolClassBuilder.build();
    }



}
