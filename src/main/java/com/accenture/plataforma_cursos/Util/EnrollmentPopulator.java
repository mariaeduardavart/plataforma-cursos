package com.accenture.plataforma_cursos.Util;

import org.springframework.stereotype.Component;

import com.accenture.plataforma_cursos.DTO.CourseDTO;
import com.accenture.plataforma_cursos.DTO.EnrollmentDTO;
import com.accenture.plataforma_cursos.DTO.StudentDTO;
import com.accenture.plataforma_cursos.Entity.Course;
import com.accenture.plataforma_cursos.Entity.Enrollment;
import com.accenture.plataforma_cursos.Entity.Student;

@Component
public class EnrollmentPopulator {

    // Converts the Student entity to StudentDTO
    public StudentDTO toStudentDTO(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .registrationDate(student.getRegistrationDate())
                .build();
    }

    // Converts the Course entity to CourseDTO
    public CourseDTO toCourseDTO(Course course) {
        return CourseDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .creationDate(course.getCreationDate())
                .build();
    }

    // Converts the Enrollment entity to EnrollmentDTO
    public EnrollmentDTO toEnrollmentDTO(Enrollment enrollment) {
        return EnrollmentDTO.builder()
                .id(enrollment.getId())
                .studentId(enrollment.getStudent().getId())
                .courseId(enrollment.getCourse().getId())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .build();
    }
}
