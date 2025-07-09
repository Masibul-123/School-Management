package com.gmmps.controller;

import com.gmmps.dto.SectionInfoDto;
import com.gmmps.service.SectionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/sections")
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("")
    public ResponseEntity<List<SectionInfoDto>> getAllSections() {
        List<SectionInfoDto> sections = sectionService.getAllSections();
        if (sections.isEmpty())
            throw new EntityNotFoundException("No sections found in the database.");
        return ResponseEntity.ok(sections);
    }

    @GetMapping("/section/{id}")
    public ResponseEntity<SectionInfoDto> getSectionById(@PathVariable long id) {
        SectionInfoDto dto = sectionService.getSectionById(id)
                .orElseThrow(() -> new EntityNotFoundException("Section not found with ID: " + id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/section")
    public ResponseEntity<SectionInfoDto> addSection(@RequestBody SectionInfoDto sectionInfoDto) {
        SectionInfoDto saved = sectionService.addSection(sectionInfoDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("section/{sectionId}")
    public ResponseEntity<SectionInfoDto> updateSectionInfo(@PathVariable long sectionId, @RequestBody SectionInfoDto sectionInfoDto) {
        SectionInfoDto updated = sectionService.updateSectionInfo(sectionId, sectionInfoDto);
        if (updated == null)
            throw new EntityNotFoundException("No section found with ID: " + sectionId);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/section/{sectionId}")
    public ResponseEntity<String> deleteSection(@PathVariable long sectionId) {
        String result = sectionService.deleteSection(sectionId);
        if (result == null)
            throw new EntityNotFoundException("No section found with ID: " + sectionId);
        return ResponseEntity.ok(result);
    }
}
