package com.example.Hospital.Controller;

import com.example.Hospital.Model.FichaMedica;
import com.example.Hospital.Service.FichaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fichamedica")
public class FichaMedicaController {

    @Autowired
    FichaMedicaService fichaMedicaService;

    @GetMapping
    public ResponseEntity<List<FichaMedica>> listarFichas() {
        List<FichaMedica> fichas = fichaMedicaService.listar();
        if (fichas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(fichas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(fichaMedicaService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<?> buscarPorPacienteId(@PathVariable Integer pacienteId) {
        try {
            return ResponseEntity.ok(fichaMedicaService.buscarPorPacienteId(pacienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/gruposanguineo/{grupoSanguineo}")
    public ResponseEntity<?> buscarPorGrupoSanguineo(@PathVariable String grupoSanguineo) {
        try {
            return ResponseEntity.ok(fichaMedicaService.buscarPorGrupoSanguineo(grupoSanguineo));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarFicha(@RequestBody FichaMedica fichaMedica) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(fichaMedicaService.guardar(fichaMedica));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarFicha(@PathVariable Integer id, @RequestBody FichaMedica fichaMedica) {
        try {
            return ResponseEntity.ok(fichaMedicaService.modificar(id, fichaMedica));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarFicha(@PathVariable Integer id) {
        try {
            fichaMedicaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
