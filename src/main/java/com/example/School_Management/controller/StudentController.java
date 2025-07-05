package com.example.School_Management.controller;


import com.example.School_Management.dto.StudentDto;
import com.example.School_Management.service.StudentService;
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
public class StudentController {

    @Autowired
    private  StudentService studentService;
    @Autowired
    private SchoolClassService  schoolClassService;


    //fetch All Students
    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {

        List<StudentDto> students = studentService.getAllStudents();

        if (students == null || students.isEmpty()) {
            throw new EntityNotFoundException("No students found in the database.");
        }

        return ResponseEntity.ok(students);

    }

    //fetch student by id
    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id)
    {
            StudentDto studentDto = studentService.getStudentById(id)
                    .orElseThrow(() -> new EntityNotFoundException("there is no student with this id :" + id));
            return ResponseEntity.ok(studentDto);
    }


    //update student by id  ,include ids when you will use put mapping
    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        StudentDto updatedStudentDto = studentService.updateStudent(id, studentDto);

        if (updatedStudentDto == null) {
            throw new EntityNotFoundException("There is no student with this ID: " + id);
        }

        return ResponseEntity.ok(updatedStudentDto);
    }

    // add,don't need to include ids when you will use post mapping
    @PostMapping("/student")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto createdStudent = studentService.saveStudent(studentDto);

        if (createdStudent == null) {
            throw new NullPointerException("Failed to create student."); // or a custom exception
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    //delete
    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        Boolean deleted = studentService.deleteStudent(id);

        if (deleted == true)
            return ResponseEntity.ok("Student with ID " + id + " has been deleted successfully.");
        else
            throw new EntityNotFoundException("No student found with ID: " + id);
    }

    //Link School Class with Student
    @PutMapping("/students/{studentId}/linkClass/{classId}")
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

