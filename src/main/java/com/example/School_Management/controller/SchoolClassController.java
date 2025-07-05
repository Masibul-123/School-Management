package com.example.School_Management.controller;


import com.example.School_Management.dto.SchoolClassDto;
import com.example.School_Management.service.SchoolClassService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class SchoolClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @GetMapping("/students/schoolClasses")
    public ResponseEntity<List<SchoolClassDto>> findAll()
    {
       List<SchoolClassDto> SchoolClassDtos=schoolClassService.findAll();
       if(SchoolClassDtos == null || SchoolClassDtos.isEmpty())
       {
           throw new EntityNotFoundException("No class Name found in the database.");
       }
       return ResponseEntity.ok(SchoolClassDtos);
    }


    @GetMapping("/students/schoolClass/{id}")
    public ResponseEntity<SchoolClassDto> findById(@PathVariable long id)
    {
        SchoolClassDto SchoolClassDto=schoolClassService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("there is no Address with this id :" + id));

        return ResponseEntity.ok(SchoolClassDto);

    }

    @PostMapping("/students/schoolClass")
    public ResponseEntity<SchoolClassDto> save(@RequestBody SchoolClassDto schoolClassDto) {
        SchoolClassDto SavedSchoolClassDto = schoolClassService.save(schoolClassDto);
        return new ResponseEntity<>(SavedSchoolClassDto, HttpStatus.CREATED);
    }

    @PutMapping("/students/schoolClass/{id}")
    public ResponseEntity<SchoolClassDto> updateSchoolClass(@PathVariable Long id,@RequestBody SchoolClassDto  schoolClassDto)
    {
        SchoolClassDto updatedSchoolClass=schoolClassService.updateSchoolClass(id,schoolClassDto);

        if(updatedSchoolClass ==null)
            throw new EntityNotFoundException("There is no class Name with this ID: " + id);
        return ResponseEntity.ok(updatedSchoolClass);
    }

    @DeleteMapping("/students/schoolClass/{id}")
    public ResponseEntity<?> deleteSchoolClass(@PathVariable Long id)
    {
        String message=schoolClassService.deleteSchoolClass(id);
        if (message == null) {
           throw new EntityNotFoundException("There is no class Name with this ID: " + id);
        }
        return ResponseEntity.ok(message); // 200 OK

    }





}