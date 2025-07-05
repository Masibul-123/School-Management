package com.example.School_Management.service;

import com.example.School_Management.dto.SchoolClassDto;
import com.example.School_Management.entity.SchoolClass;
import com.example.School_Management.repository.SchoolClassRepository;
import com.example.School_Management.repository.SectionRepository;
import com.example.School_Management.transformer.SchoolClassTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolClassService {

    @Autowired
    private  SchoolClassRepository schoolClassRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SchoolClassTransformer schoolClassTransformer;




    public List<SchoolClassDto> findAll() {

        return schoolClassRepository.findAll()
                .stream()
                .map(schoolClassTransformer::convertEntityToDto)
                .collect(Collectors.toList());

    }

    public Optional<SchoolClassDto> findById(long id) {
        return schoolClassRepository.findById(id)
                .stream()
                .map(schoolClassTransformer::convertEntityToDto)
                .findFirst();

    }

    public SchoolClassDto save(SchoolClassDto schoolClassDto) {

       SchoolClass schoolClass= schoolClassTransformer.convertDtoToEntity(schoolClassDto);

        //save section
        schoolClass.setSection(sectionRepository.save(schoolClass.getSection()));

        return schoolClassTransformer.convertEntityToDto(schoolClassRepository.save(schoolClass));

    }

    public  SchoolClassDto updateSchoolClass(long id, SchoolClassDto schoolClassDto) {

        if( !schoolClassRepository.existsById(id)){
            return null;
        }
        SchoolClass updatedClassName =schoolClassTransformer.convertDtoToEntity(schoolClassDto);

        SchoolClass returnedClassName = schoolClassRepository.save(updatedClassName);

        return schoolClassTransformer.convertEntityToDto(returnedClassName);
    }

    public  String deleteSchoolClass(Long id) {
        if(!schoolClassRepository.existsById(id))
            return null;

        schoolClassRepository.deleteById(id);
        return "ClassName with ID " + id + " deleted successfully.";
    }

    public Boolean existById(Long id)
    {
        return schoolClassRepository.existsById(id);
    }



}
