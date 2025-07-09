package com.gmmps.service;

import com.gmmps.dto.StudentDto;
import com.gmmps.entity.*;
import com.gmmps.repository.*;
import com.gmmps.transformer.StudentTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private  final StudentTransformer studentTransformer;
    private final ParentRepository parentRepository;
    private final AddressRepository addressRepository;
    private final ClassInfoRepository classInfoRepository;
    private  final SectionInfoRepository sectionInfoRepository;
    private  final StudentRepository studentRepository;

    public StudentService(StudentTransformer studentTransformer, ParentRepository parentRepository, AddressRepository addressRepository, ClassInfoRepository classInfoRepository, SectionInfoRepository sectionInfoRepository, StudentRepository studentRepository) {
        this.studentTransformer = studentTransformer;
        this.parentRepository = parentRepository;
        this.addressRepository = addressRepository;
        this.classInfoRepository = classInfoRepository;
        this.sectionInfoRepository = sectionInfoRepository;
        this.studentRepository = studentRepository;
    }


    @Transactional
    public StudentDto assignStudentToClassAndSection(Long studentId, Long classId,Long sectionId) {
        ClassInfo classInfo= classInfoRepository.findById(classId)
                .orElse(null);
        SectionInfo sectionInfo=sectionInfoRepository.findById(sectionId)
                .orElse(null);
        Student student=studentRepository.findById(studentId)
                .orElse(null);

        if( student != null)
        {
            student.setClassInfo(classInfo);
            student.setSectionInfo(sectionInfo);
        }
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
    public StudentDto updateStudent(Long studentId, StudentDto studentDto) {

        if(!studentRepository.existsById(studentId))
            return null;
        Student student = studentRepository.findById(studentId)
                .orElse(null);
        Student updatedStudent =studentTransformer.convertDtoToEntity(studentDto);
        updatedStudent.setId(studentId);
        assert student != null;
        updatedStudent.getAddress().setId(student.getAddress().getId());
        updatedStudent.getParent().setId(student.getParent().getId());
        updatedStudent.setClassInfo(student.getClassInfo());
        updatedStudent.setSectionInfo(student.getSectionInfo());
        Student returnedStudent = studentRepository.save(updatedStudent);
        return studentTransformer.convertEntityToDto(returnedStudent);
    }

    @Transactional
    public StudentDto createStudent(StudentDto studentDto) {
    Student student = studentTransformer.convertDtoToEntity(studentDto);
    student.setClassInfo(null);
    student.setSectionInfo(null);
    Parent savedParent = parentRepository.save(student.getParent());
    student.setParent(savedParent);
    Address savedAddress = addressRepository.save(student.getAddress());
    student.setAddress(savedAddress);
    Student savedStudent = studentRepository.save(student);
    return studentTransformer.convertEntityToDto(savedStudent);
}

    @Transactional
    public Boolean deleteStudent(Long id) {
        if ( studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        else
            return  false;
    }

    public Boolean studentExistById(Long id)
    {
        return studentRepository.existsById(id);
    }

}
