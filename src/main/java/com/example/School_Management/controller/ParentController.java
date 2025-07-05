package com.example.School_Management.controller;





import com.example.School_Management.dto.ParentDto;
import com.example.School_Management.service.ParentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ParentController {

    @Autowired
    private ParentService parentService;

    @GetMapping("/students/parents")
    public ResponseEntity<List<ParentDto>> findAll() {
        List<ParentDto> parentDtos = parentService.findAll();
        if (parentDtos == null || parentDtos.isEmpty()) {
            throw new EntityNotFoundException("No parent records found in the database.");
        }
        return ResponseEntity.ok(parentDtos);
    }

    @GetMapping("/students/parent/{id}")
    public ResponseEntity<ParentDto> findById(@PathVariable long id) {
        ParentDto parentDto = parentService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No parent found with ID: " + id));
        return ResponseEntity.ok(parentDto);
    }


    @PutMapping("/students/parent/{id}")
    public ResponseEntity<ParentDto> update(@PathVariable long id, @RequestBody ParentDto parentDto) {
        ParentDto updatedParent = parentService.update(id, parentDto);
        if (updatedParent == null) {
            throw new EntityNotFoundException("No parent found with ID: " + id);
        }
        return ResponseEntity.ok(updatedParent);
    }

    @DeleteMapping("/students/parent/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        String message = parentService.deleteById(id);
        if (message == null) {
            throw new EntityNotFoundException("No parent found with ID: " + id);
        }
        return ResponseEntity.ok(message);
    }
}
