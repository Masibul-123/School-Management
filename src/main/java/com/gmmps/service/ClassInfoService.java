package com.gmmps.service;

import com.gmmps.dto.ClassInfoDto;
import com.gmmps.entity.ClassInfo;
import com.gmmps.repository.ClassInfoRepository;
import com.gmmps.repository.SectionRepository;
import com.gmmps.transformer.ClassInfoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassInfoService {

    @Autowired
    private ClassInfoRepository classInfoRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ClassInfoTransformer classInfoTransformer;

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
        schoolClass.setSection(sectionRepository.save(schoolClass.getSection()));
        return classInfoTransformer.convertEntityToDto(classInfoRepository.save(schoolClass));

    }

    @Transactional
    public ClassInfoDto updateClassInfo(long id, ClassInfoDto schoolClassDto) {

        if( !classInfoRepository.existsById(id)){
            return null;
        }
        ClassInfo updatedClassName = classInfoTransformer.convertDtoToEntity(schoolClassDto);

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

    public Boolean existById(Long id) {
        return classInfoRepository.existsById(id);
    }

}
