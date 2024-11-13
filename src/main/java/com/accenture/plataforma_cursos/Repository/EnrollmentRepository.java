package com.accenture.plataforma_cursos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.plataforma_cursos.Entity.Course;
import com.accenture.plataforma_cursos.Entity.Enrollment;
import com.accenture.plataforma_cursos.Entity.Student;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Find all courses by student ID.
    List<Course> findCoursesByStudentId(Long studentId);

    // Find all students by course ID.
    List<Student> findStudentsByCourseId(Long courseId);

}
