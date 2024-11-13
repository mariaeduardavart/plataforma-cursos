package com.accenture.plataforma_cursos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.plataforma_cursos.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
