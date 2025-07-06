package com.example.School_Management.controller;

import com.example.School_Management.dto.SectionDto;
import com.example.School_Management.service.SectionService;
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
@RequestMapping("/students/sections")
@Tag(name = "section", description = "Find All,Find BY Id,Save,Update ,Delete By Id")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping("")
    @Operation(summary = "Get all sections", description = "Returns a list of all sections")
    public ResponseEntity<List<SectionDto>> findAll() {
        List<SectionDto> sections = sectionService.findAll();
        if (sections.isEmpty()) {
            throw new EntityNotFoundException("No sections found in the database.");
        }
        return ResponseEntity.ok(sections);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get section By Id", description = "Returns a section by Id")

    public ResponseEntity<SectionDto> findById(@PathVariable long id) {
        SectionDto dto = sectionService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Section not found with ID: " + id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("")
    @Operation(summary = "Add section", description = "Returns saved section,don't need to provide ids")
    public ResponseEntity<SectionDto> save(@RequestBody SectionDto sectionDto) {

        SectionDto saved = sectionService.save(sectionDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update section details", description = "Returns updated section, id is required ")
    public ResponseEntity<SectionDto> update(@PathVariable long id, @RequestBody SectionDto sectionDto) {
        SectionDto updated = sectionService.update(id, sectionDto);
        if (updated == null) {
            throw new EntityNotFoundException("No section found with ID: " + id);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete section by id", description = "delete section by id")
    public ResponseEntity<String> delete(@PathVariable long id) {
        String result = sectionService.deleteById(id);
        if (result == null) {
            throw new EntityNotFoundException("No section found with ID: " + id);
        }
        return ResponseEntity.ok(result);
    }
}
