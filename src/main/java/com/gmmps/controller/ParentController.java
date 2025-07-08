package com.gmmps.controller;





import com.gmmps.dto.ParentDto;
import com.gmmps.service.ParentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students/parents")
@Tag(name = "Parent", description = "Find All,Find BY Id,Save,Update ,Delete By Id")
public class ParentController {

    @Autowired
    private ParentService parentService;

    @GetMapping("")
    @Operation(summary = "Get all Parents", description = "Returns a list of all Parents")
    public ResponseEntity<List<ParentDto>> findAll() {
        List<ParentDto> parentDtos = parentService.findAll();
        if (parentDtos == null || parentDtos.isEmpty()) {
            throw new EntityNotFoundException("No parent records found in the database.");
        }
        return ResponseEntity.ok(parentDtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get all Parent", description = "Returns a  Parent")
    public ResponseEntity<ParentDto> findById(@PathVariable long id) {
        ParentDto parentDto = parentService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No parent found with ID: " + id));
        return ResponseEntity.ok(parentDto);
    }


    @PutMapping("/{id}")
    @Operation(summary = "update Parent  details", description = "Returns updated Parent  details ,id should be mentioned")
    public ResponseEntity<ParentDto> update(@PathVariable long id, @RequestBody ParentDto parentDto) {
        ParentDto updatedParent = parentService.update(id, parentDto);
        if (updatedParent == null) {
            throw new EntityNotFoundException("No parent found with ID: " + id);
        }
        return ResponseEntity.ok(updatedParent);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete Parent by id", description = "id should be mentioned")
    public ResponseEntity<String> delete(@PathVariable long id) {
        String message = parentService.deleteById(id);
        if (message == null) {
            throw new EntityNotFoundException("No parent found with ID: " + id);
        }
        return ResponseEntity.ok(message);
    }
}
