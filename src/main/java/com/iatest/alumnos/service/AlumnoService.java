package com.iatest.alumnos.service;

import com.iatest.alumnos.entity.Alumno;
import com.iatest.alumnos.repository.AlumnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlumnoService {
    
    private final AlumnoRepository alumnoRepository;
    
    public List<Alumno> obtenerTodos() {
        return alumnoRepository.findAll();
    }
    
    public Optional<Alumno> obtenerPorId(Integer id) {
        return alumnoRepository.findById(id);
    }
    
    public Alumno crear(Alumno alumno) {
        if (alumnoRepository.findByEmail(alumno.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        return alumnoRepository.save(alumno);
    }
    
    public Alumno actualizar(Integer id, Alumno alumnoActualizado) {
        return alumnoRepository.findById(id).map(alumno -> {
            alumno.setNombre(alumnoActualizado.getNombre());
            alumno.setTelefono(alumnoActualizado.getTelefono());
            alumno.setEstado(alumnoActualizado.getEstado());
            return alumnoRepository.save(alumno);
        }).orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado"));
    }
    
    public void eliminar(Integer id) {
        alumnoRepository.deleteById(id);
    }
    
    public List<Alumno> obtenerPorEstado(String estado) {
        return alumnoRepository.findByEstado(estado);
    }
}
