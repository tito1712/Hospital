package com.example.Hospital.Service;

import com.example.Hospital.Model.Medicamento;
import com.example.Hospital.Repository.MedicamentoRepository;
import com.example.Hospital.Repository.MedicoRepository;
import com.example.Hospital.Repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedicamentoService {

    @Autowired
    MedicamentoRepository medicamentoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    MedicoRepository medicoRepository;

    public List<Medicamento> listar() {
        return medicamentoRepository.findAll();
    }

    public Medicamento buscarPorId(Integer id) {
        return medicamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
    }

    public List<Medicamento> buscarPorNombre(String nombre) {
        List<Medicamento> medicamentos = medicamentoRepository.findByNombre(nombre);
        if (medicamentos.isEmpty())
            throw new RuntimeException("No se encontraron medicamentos con ese nombre");
        return medicamentos;
    }

    public List<Medicamento> buscarPorPacienteId(Integer pacienteId) {
        if (!pacienteRepository.existsById(pacienteId))
            throw new RuntimeException("Paciente no encontrado");
        List<Medicamento> medicamentos = medicamentoRepository.findByPacienteId(pacienteId);
        if (medicamentos.isEmpty())
            throw new RuntimeException("No se encontraron medicamentos para ese paciente");
        return medicamentos;
    }

    public List<Medicamento> buscarPorMedicoId(Integer medicoId) {
        if (!medicoRepository.existsById(medicoId))
            throw new RuntimeException("Medico no encontrado");
        List<Medicamento> medicamentos = medicamentoRepository.findByMedicoId(medicoId);
        if (medicamentos.isEmpty())
            throw new RuntimeException("No se encontraron medicamentos para ese medico");
        return medicamentos;
    }

    public Medicamento guardar(Medicamento medicamento) {
        if (medicamento.getId() != null)
            throw new RuntimeException("No se debe enviar un ID al crear un medicamento");
        pacienteRepository.findById(medicamento.getPaciente().getId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        medicoRepository.findById(medicamento.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
        return medicamentoRepository.save(medicamento);
    }

    public Medicamento modificar(Integer id, Medicamento datos) {
        if (!medicamentoRepository.existsById(id))
            throw new RuntimeException("Medicamento no encontrado");
        if (datos.getPaciente() != null && datos.getPaciente().getId() != null
                && !pacienteRepository.existsById(datos.getPaciente().getId()))
            throw new RuntimeException("Paciente no encontrado");
        if (datos.getMedicoId() != null && !medicoRepository.existsById(datos.getMedicoId()))
            throw new RuntimeException("Medico no encontrado");
        Medicamento existente = medicamentoRepository.getReferenceById(id);
        Optional.ofNullable(datos.getNombre()).ifPresent(existente::setNombre);
        Optional.ofNullable(datos.getDescripcion()).ifPresent(existente::setDescripcion);
        Optional.ofNullable(datos.getMedicoId()).ifPresent(existente::setMedicoId);
        Optional.ofNullable(datos.getPaciente()).ifPresent(existente::setPaciente);
        return medicamentoRepository.save(existente);
    }

    public void eliminar(Integer id) {
        if (!medicamentoRepository.existsById(id))
            throw new RuntimeException("Medicamento no encontrado");
        medicamentoRepository.deleteById(id);
    }
}
