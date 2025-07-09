package com.gmmps.controller;

import com.gmmps.dto.ClassInfoDto;
import com.gmmps.service.ClassInfoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassInfoController {

    private final ClassInfoService classInfoService;
    public ClassInfoController(ClassInfoService classInfoService) {
        this.classInfoService = classInfoService;
    }

    @GetMapping("")
    public ResponseEntity<List<ClassInfoDto>> getAllClassInfos() {
       List<ClassInfoDto> classInfoDtos= classInfoService.getAllClassInfos();
       if(classInfoDtos == null || classInfoDtos.isEmpty()) {
           throw new EntityNotFoundException("No class Name found in the database.");
       }
       return ResponseEntity.ok(classInfoDtos);
    }

    @GetMapping("/class/{id}")
    public ResponseEntity<ClassInfoDto> getClassInfoByClassId(@PathVariable long id) {
        ClassInfoDto classInfoDto= classInfoService.getClassInfoByClassId(id)
                .orElseThrow(() -> new EntityNotFoundException("there is no Address with this id :" + id));
        return ResponseEntity.ok(classInfoDto);
    }

    @PostMapping("/class")
    public ResponseEntity<ClassInfoDto> addClassInfo(@RequestBody ClassInfoDto classInfoDto) {
        ClassInfoDto SavedclassInfoDto = classInfoService.addClassInfo(classInfoDto);
        return new ResponseEntity<>(SavedclassInfoDto, HttpStatus.CREATED);
    }

    @PutMapping("/class/{id}")
    public ResponseEntity<ClassInfoDto> updateClassInfo(@PathVariable Long id, @RequestBody ClassInfoDto schoolClassDto) {
        ClassInfoDto updatedSchoolClass= classInfoService.updateClassInfo(id,schoolClassDto);

        if(updatedSchoolClass ==null)
            throw new EntityNotFoundException("There is no class Name with this ID: " + id);
        return ResponseEntity.ok(updatedSchoolClass);
    }

    @DeleteMapping("/class/{id}")
    public ResponseEntity<?> deleteClassInfo(@PathVariable Long id) {
        String message= classInfoService.deleteClassInfo(id);
        if (message == null) {
           throw new EntityNotFoundException("There is no class Name with this ID: " + id);
        }
        return ResponseEntity.ok(message);
    }

}