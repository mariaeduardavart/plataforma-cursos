package com.accenture.plataforma_cursos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.plataforma_cursos.DTO.CourseDTO;
import com.accenture.plataforma_cursos.Entity.Course;
import com.accenture.plataforma_cursos.Exceptions.CourseNotFoundException;
import com.accenture.plataforma_cursos.Repository.CourseRepository;
import com.accenture.plataforma_cursos.Util.EnrollmentPopulator;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentPopulator enrollmentPopulator;

    public CourseDTO registerCourse(Course course) {

        if (course.getName() == null || course.getDescription() == null) {
            throw new IllegalArgumentException("Course name and description cannot be null");
        }

        Course savedCourse = courseRepository.save(course);
        return enrollmentPopulator.toCourseDTO(savedCourse);
    }

    public CourseDTO getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + courseId));
        return enrollmentPopulator.toCourseDTO(course);
    }
}
