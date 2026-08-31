package com.iatest.alumnos.controller;

import com.iatest.alumnos.entity.Alumno;
import com.iatest.alumnos.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AlumnoController {
    
    private final AlumnoService alumnoService;
    
    @GetMapping
    public ResponseEntity<List<Alumno>> obtenerTodos() {
        return ResponseEntity.ok(alumnoService.obtenerTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtenerPorId(@PathVariable Integer id) {
        return alumnoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Alumno> crear(@RequestBody Alumno alumno) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.crear(alumno));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizar(@PathVariable Integer id, @RequestBody Alumno alumno) {
        try {
            return ResponseEntity.ok(alumnoService.actualizar(id, alumno));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        alumnoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Alumno>> obtenerPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(alumnoService.obtenerPorEstado(estado));
    }
}
