package com.example.Hospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Hospital.Model.Medico;
import com.example.Hospital.Service.MedicoService;

@RestController
@RequestMapping("/api/v1/medico")
public class MedicoController {

    @Autowired
    MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> listarMedicos() {
        List<Medico> medicos = medicoService.listar();
        if (medicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            Medico medico = medicoService.buscarPorId(id);
            return ResponseEntity.ok(medico);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/run/{runMedico}")
    public ResponseEntity<?> buscarPorRun(@PathVariable Integer runMedico) {
        try {
            Medico medico = medicoService.buscarPorRun(runMedico);
            return ResponseEntity.ok(medico);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {
        try {
            List<Medico> medicos = medicoService.buscarPorNombre(nombre);
            return ResponseEntity.ok(medicos);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<?> buscarPorApellido(@PathVariable String apellido) {
        try {
            List<Medico> medicos = medicoService.buscarPorApellido(apellido);
            return ResponseEntity.ok(medicos);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/nombre/{nombre}/apellido/{apellido}")
    public ResponseEntity<?> buscarPorNombreYApellido(@PathVariable String nombre, @PathVariable String apellido) {
        try {
            List<Medico> medicos = medicoService.buscarPorNombreYApellido(nombre, apellido);
            return ResponseEntity.ok(medicos);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<?> buscarPorEspecialidad(@PathVariable String especialidad) {
        try {
            List<Medico> medicos = medicoService.buscarPorEspecialidad(especialidad);
            return ResponseEntity.ok(medicos);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarMedico(@RequestBody Medico medico) {
        try {
            Medico medicoGuardado = medicoService.guardar(medico);
            return ResponseEntity.status(HttpStatus.CREATED).body(medicoGuardado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarMedico(@PathVariable Integer id, @RequestBody Medico medico) {
        try {
            Medico medicoActualizado = medicoService.modificar(id, medico);
            return ResponseEntity.ok(medicoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMedico(@PathVariable Integer id) {
        try {
            medicoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
