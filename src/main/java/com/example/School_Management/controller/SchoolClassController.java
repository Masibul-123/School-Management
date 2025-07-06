package com.example.School_Management.controller;


import com.example.School_Management.dto.SchoolClassDto;
import com.example.School_Management.service.SchoolClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "School class", description = "Find All,Find BY Id,Save ,Update ,Delete By Id")
@RequestMapping("/students/schoolClasses")
public class SchoolClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @GetMapping("")
    @Operation(summary = "Get all School class linked with section", description = "Returns a list of all School class")
    public ResponseEntity<List<SchoolClassDto>> findAll()
    {
       List<SchoolClassDto> SchoolClassDtos=schoolClassService.findAll();
       if(SchoolClassDtos == null || SchoolClassDtos.isEmpty())
       {
           throw new EntityNotFoundException("No class Name found in the database.");
       }
       return ResponseEntity.ok(SchoolClassDtos);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get school class By Id", description = "Returns a school class by Id")
    public ResponseEntity<SchoolClassDto> findById(@PathVariable long id)
    {
        SchoolClassDto SchoolClassDto=schoolClassService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("there is no Address with this id :" + id));

        return ResponseEntity.ok(SchoolClassDto);

    }

    @PostMapping("")
    @Operation(summary = "Add School class with section name", description = "Returns saved section,don't need to provide ids")
    public ResponseEntity<SchoolClassDto> save(@RequestBody SchoolClassDto schoolClassDto) {
        SchoolClassDto SavedSchoolClassDto = schoolClassService.save(schoolClassDto);
        return new ResponseEntity<>(SavedSchoolClassDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update school class details", description = "Returns updated school class, ids are required ")
    public ResponseEntity<SchoolClassDto> updateSchoolClass(@PathVariable Long id,@RequestBody SchoolClassDto  schoolClassDto)
    {
        SchoolClassDto updatedSchoolClass=schoolClassService.updateSchoolClass(id,schoolClassDto);

        if(updatedSchoolClass ==null)
            throw new EntityNotFoundException("There is no class Name with this ID: " + id);
        return ResponseEntity.ok(updatedSchoolClass);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete school class", description = "delete school  by id, together with associated section will be also deleted")
    public ResponseEntity<?> deleteSchoolClass(@PathVariable Long id)
    {
        String message=schoolClassService.deleteSchoolClass(id);
        if (message == null) {
           throw new EntityNotFoundException("There is no class Name with this ID: " + id);
        }
        return ResponseEntity.ok(message); // 200 OK

    }





}