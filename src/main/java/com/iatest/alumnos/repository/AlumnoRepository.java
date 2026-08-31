package com.iatest.alumnos.repository;

import com.iatest.alumnos.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    List<Alumno> findByEstado(String estado);
    Optional<Alumno> findByEmail(String email);
}
