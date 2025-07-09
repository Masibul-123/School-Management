package com.gmmps.controller;

import com.gmmps.dto.ParentDto;
import com.gmmps.service.ParentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/parents")
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping("")
    public ResponseEntity<List<ParentDto>> getAllParents() {
        List<ParentDto> parentDtos = parentService.getAllParents();
        if (parentDtos == null || parentDtos.isEmpty())
            throw new EntityNotFoundException("No parent records found in the database.");
        return ResponseEntity.ok(parentDtos);
    }

    @GetMapping("/parent/{id}")
    public ResponseEntity<ParentDto> getParentById(@PathVariable long id) {
        ParentDto parentDto = parentService.getParentById(id)
                .orElseThrow(() -> new EntityNotFoundException("No parent found with ID: " + id));
        return ResponseEntity.ok(parentDto);
    }

    @DeleteMapping("/parent/{id}")
    public ResponseEntity<String> deleteParentDetails(@PathVariable long id) {
        String message = parentService.deleteParentDetails(id);
        if (message == null)
            throw new EntityNotFoundException("No parent found with ID: " + id);
        return ResponseEntity.ok(message);
    }
}
