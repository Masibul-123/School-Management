package com.gmmps.service;


import com.gmmps.dto.StudentDto;
import com.gmmps.entity.*;
import com.gmmps.repository.*;
import com.gmmps.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentTransformer studentTransformer;
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClassInfoRepository classInfoRepository;


    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public StudentDto assignStudentToClass(Long studentId, Long classId) {
        ClassInfo classInfo= classInfoRepository.findById(classId)
                .orElse(null);
        Student student=studentRepository.findById(studentId)
                .orElse(null);

        if( student != null)
            student.setClassInfo(classInfo);
        assert student != null;
        return studentTransformer.convertEntityToDto(studentRepository.save(student));
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentTransformer::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<StudentDto> getStudentById(Long id) {

        return studentRepository.findById(id)
                .stream()
                .map(studentTransformer::convertEntityToDto)
                .findFirst();
    }

    @Transactional
    public StudentDto updateStudent(Long id, StudentDto studentDto) {

        if(!studentRepository.existsById(id))
            return null;

        Student student = studentRepository.findById(id)
                .orElse(null);
        Student updatedStudent =studentTransformer.convertDtoToEntity(studentDto);
        Student returnedStudent = studentRepository.save(updatedStudent);
        return studentTransformer.convertEntityToDto(returnedStudent);

    }
    @Transactional
    public StudentDto saveStudent(StudentDto studentDto) {
    // Convert DTO to entity (incomplete references)
    Student student = studentTransformer.convertDtoToEntity(studentDto);

    // Fetch and set SchoolClass if ID is provided
    if (studentDto.getClassInfoDto() != null && studentDto.getClassInfoDto().getId() != 0) {
        ClassInfo classInfo = classInfoRepository.findById(studentDto.getClassInfoDto().getId())
                .orElse(null);
        student.setClassInfo(classInfo);
    }

    // Save Parent
    Parent savedParent = parentRepository.save(student.getParent());
    student.setParent(savedParent);

    // Save Address
    Address savedAddress = addressRepository.save(student.getAddress());
    student.setAddress(savedAddress);

    // Save Student
    Student savedStudent = studentRepository.save(student);
    return studentTransformer.convertEntityToDto(savedStudent);
}


    @Transactional
    public Boolean deleteStudent(Long id) {
        if ( studentRepository.existsById(id)) {
            
            List<Payment> paymentList=paymentRepository.findAll();
            for(Payment payment:paymentList)
            {
                if(id.equals(payment.getStudent().getId()))
                {
                    Long paymentId=payment.getId();
                    paymentRepository.deleteById(paymentId);
                }

            }
            studentRepository.deleteById(id);
            return true;
        }
        else {
            return  false;
        }
    }

    public Boolean existById(Long id)
    {
        return studentRepository.existsById(id);
    }



}
