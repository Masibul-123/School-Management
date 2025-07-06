package com.example.School_Management.controller;


import com.example.School_Management.dto.StudentDto;
import com.example.School_Management.service.StudentService;
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
@RequestMapping("/students")
@Tag(name = "Student", description = "Find All,Find BY Id,Save,Update ,Delete By Id,Link with class")
public class StudentController {

    @Autowired
    private  StudentService studentService;
    @Autowired
    private SchoolClassService  schoolClassService;


    //fetch All Students
    @GetMapping("")
    @Operation(summary = "Get all students", description = "Returns a list of all students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {

        List<StudentDto> students = studentService.getAllStudents();

        if (students == null || students.isEmpty()) {
            throw new EntityNotFoundException("No students found in the database.");
        }

        return ResponseEntity.ok(students);

    }

    //fetch student by id
    @GetMapping("/{id}")
    @Operation(summary = "Get student By Id", description = "Returns a student by Id")
    public ResponseEntity<?> getStudentById(@PathVariable Long id)
    {
            StudentDto studentDto = studentService.getStudentById(id)
                    .orElseThrow(() -> new EntityNotFoundException("there is no student with this id :" + id));
            return ResponseEntity.ok(studentDto);
    }


    //update student by id  ,include ids when you will use put mapping
    @PutMapping("/{id}")
    @Operation(summary = "update student details", description = "Returns updated student ,ids should be present")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        StudentDto updatedStudentDto = studentService.updateStudent(id, studentDto);

        if (updatedStudentDto == null) {
            throw new EntityNotFoundException("There is no student with this ID: " + id);
        }

        return ResponseEntity.ok(updatedStudentDto);
    }

    // add,don't need to include ids when you will use post mapping
    @PostMapping("")
    @Operation(summary = "Add student details with parent and Address details", description = "Returns saved student,no need to give any id")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto createdStudent = studentService.saveStudent(studentDto);

        if (createdStudent == null) {
            throw new NullPointerException("Failed to create student."); // or a custom exception
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    //delete
    @DeleteMapping("/{id}")
    @Operation(summary = "delete student", description = "delete student by id")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        Boolean deleted = studentService.deleteStudent(id);

        if (deleted == true)
            return ResponseEntity.ok("Student with ID " + id + " has been deleted successfully.");
        else
            throw new EntityNotFoundException("No student found with ID: " + id);
    }

    //Link School Class with Student
    @PutMapping("/{studentId}/linkClass/{classId}")
    @Operation(summary = "Add school class to a student", description = "Returns a student with linked school class")
    public ResponseEntity<?> studentLinkClass(@PathVariable Long studentId,@PathVariable Long classId )
    {
            if(studentService.existById(studentId) && schoolClassService.existById(classId))
            {
                StudentDto studentDto=studentService.getStudentById(studentId)
                        .orElseThrow(()->new NullPointerException("No student/class found with ID: " ));

                return ResponseEntity.ok(studentService.studentLinkClass(studentId,classId));

            }
            else
                throw new NullPointerException("No student/class found with ID: " );
    }



}

