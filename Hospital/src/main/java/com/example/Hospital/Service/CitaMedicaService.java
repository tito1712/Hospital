package com.example.Hospital.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Hospital.Model.Citamedica;
import com.example.Hospital.Repository.CitamedicaRepository;
import com.example.Hospital.Repository.MedicoRepository;
import com.example.Hospital.Repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CitaMedicaService {

    @Autowired
    CitamedicaRepository citaMedicaRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    MedicoRepository medicoRepository;

    public List<Citamedica> listar() {
        return citaMedicaRepository.findAll();
    }

    public Citamedica buscarPorId(Integer id) {
        return citaMedicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita medica no encontrada"));
    }

    public List<Citamedica> buscarPorPacienteId(Integer pacienteId) {
        List<Citamedica> citas = citaMedicaRepository.findByPacienteId(pacienteId);
        if (citas.isEmpty())
            throw new RuntimeException("No se encontraron citas para el paciente");
        return citas;
    }

    public List<Citamedica> buscarPorMedicoId(Integer medicoId) {
        List<Citamedica> citas = citaMedicaRepository.findByMedicoId(medicoId);
        if (citas.isEmpty())
            throw new RuntimeException("No se encontraron citas para el medico");
        return citas;
    }

    public List<Citamedica> buscarPorFecha(LocalDate fecha) {
        List<Citamedica> citas = citaMedicaRepository.findByFecha(fecha);
        if (citas.isEmpty())
            throw new RuntimeException("No se encontraron citas para esa fecha");
        return citas;
    }

    public List<Citamedica> buscarPorHora(LocalTime hora) {
        List<Citamedica> citas = citaMedicaRepository.findByHora(hora);
        if (citas.isEmpty())
            throw new RuntimeException("No se encontraron citas para esa hora");
        return citas;
    }

    public Citamedica guardar(Citamedica cita) {
        if (cita.getId() != null)
            throw new RuntimeException("No se debe enviar un ID al crear una cita medica");
        pacienteRepository.findById(cita.getPaciente().getId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        medicoRepository.findById(cita.getMedico().getId())
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
        return citaMedicaRepository.save(cita);
    }

    public Citamedica modificar(Integer id, Citamedica datos) {
        if (!citaMedicaRepository.existsById(id))
            throw new RuntimeException("Cita medica no encontrada");
        if (datos.getPaciente().getId() != null && !pacienteRepository.existsById(datos.getPaciente().getId()))
            throw new RuntimeException("Paciente no encontrado");
        if (datos.getMedico().getId() != null && !medicoRepository.existsById(datos.getMedico().getId()))
            throw new RuntimeException("Medico no encontrado");
        Citamedica existente = citaMedicaRepository.getReferenceById(id);
        Optional.ofNullable(datos.getFecha()).ifPresent(existente::setFecha);
        Optional.ofNullable(datos.getHora()).ifPresent(existente::setHora);
        Optional.ofNullable(datos.getCosto()).ifPresent(existente::setCosto);
        Optional.ofNullable(datos.getDiagnostico()).ifPresent(existente::setDiagnostico);
        Optional.ofNullable(datos.getPaciente()).ifPresent(existente::setPaciente);
        Optional.ofNullable(datos.getMedico()).ifPresent(existente::setMedico);
        return citaMedicaRepository.save(existente);
    }

    public void eliminar(Integer id) {
        if (!citaMedicaRepository.existsById(id))
            throw new RuntimeException("Cita medica no encontrada");
        citaMedicaRepository.deleteById(id);
    }

}
