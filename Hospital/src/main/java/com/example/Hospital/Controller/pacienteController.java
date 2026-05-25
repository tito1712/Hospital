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

import com.example.Hospital.Model.Paciente;
import com.example.Hospital.Service.PacienteService;

@RestController
@RequestMapping("/api/v1/pacientes")
public class pacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        if (pacientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {
        try {
            List<Paciente> pacientes = pacienteService.buscarPorNombre(nombre);
            return ResponseEntity.ok(pacientes);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            Paciente paciente = pacienteService.buscarPorId(id);
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<?> buscarPorCorreo(@PathVariable String correo) {
        try {
            Paciente paciente = pacienteService.buscarPorCorreo(correo);
            return ResponseEntity.ok(paciente);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/telefono/{telefono}")
    public ResponseEntity<?> buscarPorTelefono(@PathVariable String Telefono) {
        try {
            Paciente paciente = pacienteService.buscarPorTelefono(Telefono);
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarPaciente(@RequestBody Paciente paciente) {
        try {
            Paciente pacienteGuardado = pacienteService.guardar(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteGuardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPaciente(@PathVariable Integer id, @RequestBody Paciente paciente) {
        try {
            Paciente pacienteActualizado = pacienteService.modificar(id, paciente);
            return ResponseEntity.ok(pacienteActualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id) {
        try {
            pacienteService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}