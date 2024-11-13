package com.accenture.plataforma_cursos.Exceptions;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message) {
        super(message);
    }
}
