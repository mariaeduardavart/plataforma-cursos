package com.accenture.plataforma_cursos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.plataforma_cursos.Entity.Course;
import com.accenture.plataforma_cursos.Entity.Enrollment;
import com.accenture.plataforma_cursos.Entity.Student;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Find all courses by student ID.
    @Query("SELECT e.course FROM Enrollment e WHERE e.student.id = :studentId")
    List<Course> findCoursesByStudentId(@Param("studentId") Long studentId);

    // Find all students by course ID.
    @Query("SELECT e.student FROM Enrollment e WHERE e.course.id = :courseId")
    List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);
}
