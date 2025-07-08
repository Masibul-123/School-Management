package com.gmmps.transformer;

import com.gmmps.dto.ClassInfoDto;
import com.gmmps.dto.SectionDto;
import com.gmmps.entity.ClassInfo;
import com.gmmps.entity.Section;
import org.springframework.stereotype.Component;

@Component
public class ClassInfoTransformer {

    public ClassInfoDto convertEntityToDto(ClassInfo classInfo) {
        return ClassInfoDto.builder()
                .id(classInfo.getId())
                .className(classInfo.getName())
                .sectionDto(SectionDto.builder()
                                .id(classInfo.getSection().getId())
                                .sectionName(classInfo.getSection().getSectionName()).build()
                )
                .build();
    }

    public ClassInfo convertDtoToEntity(ClassInfoDto classInfoDto) {
        Section.SectionBuilder sectionBuilder = Section.builder()
                .sectionName(classInfoDto.getSectionDto().getSectionName());
        if (classInfoDto.getSectionDto().getId() != null)
            sectionBuilder.id(classInfoDto.getSectionDto().getId());

        ClassInfo.ClassInfoBuilder classInfoBuilder = ClassInfo.builder()
                .name(classInfoDto.getClassName())
                .section(sectionBuilder.build());
        if (classInfoDto.getId() != null)
            classInfoBuilder.id(classInfoDto.getId());

        return classInfoBuilder.build();
    }



}
