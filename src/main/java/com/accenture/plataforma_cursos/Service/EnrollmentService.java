package com.accenture.plataforma_cursos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.plataforma_cursos.DTO.EnrollmentDTO;
import com.accenture.plataforma_cursos.Repository.CourseRepository;
import com.accenture.plataforma_cursos.Repository.EnrollmentRepository;

import java.util.List;

import com.accenture.plataforma_cursos.Entity.Course;
import com.accenture.plataforma_cursos.Entity.Enrollment;
import com.accenture.plataforma_cursos.Entity.Student;
import com.accenture.plataforma_cursos.Exceptions.EnrollmentException;
import com.accenture.plataforma_cursos.Repository.StudentRepository;
import com.accenture.plataforma_cursos.Util.EnrollmentPopulator;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentPopulator enrollmentPopulator;

    public EnrollmentDTO enrollStudentInCourse(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EnrollmentException("Student not found with ID: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EnrollmentException("Course not found with ID: " + courseId));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(java.time.LocalDateTime.now());

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return enrollmentPopulator.toEnrollmentDTO(savedEnrollment);
    }

    public List<Course> getCoursesByStudentId(Long studentId) {
        return enrollmentRepository.findCoursesByStudentId(studentId);
    }

    public List<Student> getStudentsByCourseId(Long courseId) {
        return enrollmentRepository.findStudentsByCourseId(courseId);
    }
}