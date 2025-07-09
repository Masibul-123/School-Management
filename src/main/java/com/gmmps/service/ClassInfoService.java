package com.gmmps.service;

import com.gmmps.dto.ClassInfoDto;
import com.gmmps.entity.ClassInfo;
import com.gmmps.repository.ClassInfoRepository;
import com.gmmps.repository.SectionInfoRepository;
import com.gmmps.transformer.ClassInfoTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassInfoService {


    private final ClassInfoRepository classInfoRepository;
    private  final ClassInfoTransformer classInfoTransformer;

    public ClassInfoService(ClassInfoRepository classInfoRepository, SectionInfoRepository sectionInfoRepository, ClassInfoTransformer classInfoTransformer) {
        this.classInfoRepository = classInfoRepository;
        this.classInfoTransformer = classInfoTransformer;
    }

    public List<ClassInfoDto> getAllClassInfos() {
        return classInfoRepository.findAll()
                .stream()
                .map(classInfoTransformer::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<ClassInfoDto> getClassInfoByClassId(long id) {
        return classInfoRepository.findById(id)
                .stream()
                .map(classInfoTransformer::convertEntityToDto)
                .findFirst();
    }

    @Transactional
    public ClassInfoDto addClassInfo(ClassInfoDto schoolClassDto) {
        ClassInfo schoolClass= classInfoTransformer.convertDtoToEntity(schoolClassDto);
        return classInfoTransformer.convertEntityToDto(classInfoRepository.save(schoolClass));
    }

    @Transactional
    public ClassInfoDto updateClassInfo(long classId, ClassInfoDto schoolClassDto) {
        if( !classInfoRepository.existsById(classId))
            return null;
        ClassInfo updatedClassName = classInfoTransformer.convertDtoToEntity(schoolClassDto);
        updatedClassName.setId(classId);
        ClassInfo returnedClassName = classInfoRepository.save(updatedClassName);
        return classInfoTransformer.convertEntityToDto(returnedClassName);
    }

    @Transactional
    public  String deleteClassInfo(Long id) {
        if(!classInfoRepository.existsById(id))
            return null;
        classInfoRepository.deleteById(id);
        return "ClassName with ID " + id + " deleted successfully.";
    }

    public Boolean classExistById(Long id) {
        return classInfoRepository.existsById(id);
    }

}
