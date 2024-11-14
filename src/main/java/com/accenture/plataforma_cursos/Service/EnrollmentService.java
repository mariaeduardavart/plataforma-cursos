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

import jakarta.transaction.Transactional;

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

    @Transactional
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
        try {
            return enrollmentRepository.findCoursesByStudentId(studentId);
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while fetching courses for student: " + ex.getMessage(), ex);
        }
    }

    public List<Student> getStudentsByCourseId(Long courseId) {
        try {
            return enrollmentRepository.findStudentsByCourseId(courseId);
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while fetching students for course: " + ex.getMessage(), ex);
        }
    }
}
