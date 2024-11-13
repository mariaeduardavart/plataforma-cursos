package com.accenture.plataforma_cursos.Exceptions;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String message) {
        super(message);
    }
}
