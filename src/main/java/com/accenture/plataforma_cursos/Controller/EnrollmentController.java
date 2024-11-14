package com.accenture.plataforma_cursos.Controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.accenture.plataforma_cursos.DTO.EnrollmentDTO;

import com.accenture.plataforma_cursos.Entity.Course;
import com.accenture.plataforma_cursos.Entity.Student;
import com.accenture.plataforma_cursos.Exceptions.EnrollmentException;

import com.accenture.plataforma_cursos.Service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentController.class);

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<?> enrollStudentInCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        try {
            logger.info("Enrolling student with ID: {} in course with ID: {}", studentId, courseId);
            EnrollmentDTO enrollmentDTO = enrollmentService.enrollStudentInCourse(studentId, courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentDTO);
        } catch (EnrollmentException ex) {
            logger.error("Error enrolling student: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Unexpected error: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "An unexpected error occurred: " + ex.getMessage()));
        }
    }

    // List all courses of a student
    @GetMapping("/courses/{studentId}")
    public ResponseEntity<?> getCoursesByStudent(@PathVariable Long studentId) {
        try {
            logger.info("Fetching courses for student with ID: {}", studentId);
            List<Course> courses = enrollmentService.getCoursesByStudentId(studentId);
            if (courses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("message", "No courses found for this student"));
            }
            return ResponseEntity.ok(courses);
        } catch (Exception ex) {
            logger.error("Error fetching courses for student: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "An unexpected error occurred: " + ex.getMessage()));
        }

    }

    //List all students enrolled in a specific course
    @GetMapping("/students/{courseId}")
    public ResponseEntity<?> getStudentsByCourse(@PathVariable Long courseId) {
        try {
            logger.info("Fetching students for course with ID: {}", courseId);
            List<Student> students = enrollmentService.getStudentsByCourseId(courseId);
            if (students.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("message", "No students found for this course"));
            }
            return ResponseEntity.ok(students);
        } catch (Exception ex) {
            logger.error("Error fetching students for course: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "An unexpected error occurred: " + ex.getMessage()));
        }
    }
}
