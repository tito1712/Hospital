package com.example.Hospital.Controller;

import com.example.Hospital.Model.Medicamento;
import com.example.Hospital.Service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicamento")
public class MedicamentoController {

    @Autowired
    MedicamentoService medicamentoService;

    @GetMapping
    public ResponseEntity<List<Medicamento>> listarMedicamentos() {
        List<Medicamento> medicamentos = medicamentoService.listar();
        if (medicamentos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(medicamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(medicamentoService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {
        try {
            return ResponseEntity.ok(medicamentoService.buscarPorNombre(nombre));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<?> buscarPorPacienteId(@PathVariable Integer pacienteId) {
        try {
            return ResponseEntity.ok(medicamentoService.buscarPorPacienteId(pacienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<?> buscarPorMedicoId(@PathVariable Integer medicoId) {
        try {
            return ResponseEntity.ok(medicamentoService.buscarPorMedicoId(medicoId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarMedicamento(@RequestBody Medicamento medicamento) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoService.guardar(medicamento));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarMedicamento(@PathVariable Integer id, @RequestBody Medicamento medicamento) {
        try {
            return ResponseEntity.ok(medicamentoService.modificar(id, medicamento));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMedicamento(@PathVariable Integer id) {
        try {
            medicamentoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
