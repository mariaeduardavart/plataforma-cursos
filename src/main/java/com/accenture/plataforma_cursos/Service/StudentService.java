package com.accenture.plataforma_cursos.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.plataforma_cursos.Controller.StudentController;
import com.accenture.plataforma_cursos.DTO.StudentDTO;
import com.accenture.plataforma_cursos.Entity.Student;
import com.accenture.plataforma_cursos.Exceptions.StudentNotFoundException;
import com.accenture.plataforma_cursos.Repository.StudentRepository;
import com.accenture.plataforma_cursos.Util.EnrollmentPopulator;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentPopulator enrollmentPopulator;

    @Transactional
    public StudentDTO registerStudent(Student student) {

        if (student.getName() == null || student.getEmail() == null) {
            throw new IllegalArgumentException("Student name and email cannot be null");
        }

        Student savedStudent = studentRepository.save(student);
        return enrollmentPopulator.toStudentDTO(savedStudent);
    }

    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));

        return enrollmentPopulator.toStudentDTO(student);
    }

}
