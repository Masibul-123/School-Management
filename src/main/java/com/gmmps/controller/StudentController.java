package com.gmmps.controller;


import com.gmmps.dto.StudentDto;
import com.gmmps.service.StudentService;
import com.gmmps.service.ClassInfoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private  final StudentService studentService;
    private final ClassInfoService classInfoService;

    public StudentController(StudentService studentService, ClassInfoService classInfoService) {
        this.studentService = studentService;
        this.classInfoService = classInfoService;
    }

    /**
     * This method is responsible to fetch all students
     * @return {@link ResponseEntity<List<StudentDto>>}
     */
    @GetMapping("")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        if (students == null || students.isEmpty()) {
            throw new EntityNotFoundException("No student found in the database.");
        }
        return ResponseEntity.ok(students);
    }

    /**
     * This method is responsible to fetch a student by using student ID.
     * @param id the ID of the student
     * @return {@link ResponseEntity<StudentDto>}
     */
    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        StudentDto studentDto = studentService.getStudentById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no student with this id :" + id));
        return ResponseEntity.ok(studentDto);
    }

    /**
     * This method is responsible to update a student by using student ID.
     * @param id the ID of the student
     * @param studentDto the updated student details
     * @return {@link ResponseEntity<StudentDto>}
     */
    @PutMapping("student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        StudentDto updatedStudentDto = studentService.updateStudent(id, studentDto);
        if (updatedStudentDto == null) {
            throw new EntityNotFoundException("There is no student found with this ID: " + id);
        }
        return ResponseEntity.ok(updatedStudentDto);
    }

    /**
     * This method is responsible to create a new student.
     * @param studentDto the details of the student to be created
     * @return {@link ResponseEntity<StudentDto>}
     */
    @PostMapping("/student")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto createdStudent = studentService.saveStudent(studentDto);
        if (createdStudent == null) {
            throw new NullPointerException("Failed to create student."); // or a custom exception
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    /**
     * This method is responsible to delete a student by using student ID.
     * @param id the ID of the student to be deleted
     * @return {@link ResponseEntity<String>}
     */
    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return ResponseEntity.ok("Student with ID " + id + " has been deleted successfully.");
        } else {
            throw new EntityNotFoundException("No student found with ID: " + id);
        }
    }

    /**
     * This method is responsible to link a student with a class.
     * @param studentId the ID of the student
     * @param classId the ID of the class
     * @return {@link ResponseEntity<?>}
     */
    @PutMapping("/student/{studentId}/class/{classId}")
    public ResponseEntity<?> assignStudentToClass(@PathVariable Long studentId,@PathVariable Long classId ) {
        if(studentService.existById(studentId) && classInfoService.existById(classId)) {
            StudentDto studentDto = studentService.getStudentById(studentId)
                        .orElseThrow(()->new NullPointerException("No student/class found with ID: " ));
            return ResponseEntity.ok(studentService.assignStudentToClass(studentId,classId));
        } else {
            throw new NullPointerException("No student/class found with ID: ");
        }
    }

}

