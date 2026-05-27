package com.example.Hospital.Service;

import com.example.Hospital.Model.FichaMedica;
import com.example.Hospital.Repository.FichaMedicaRepository;
import com.example.Hospital.Repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FichaMedicaService {

    @Autowired
    FichaMedicaRepository fichaMedicaRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    public List<FichaMedica> listar() {
        return fichaMedicaRepository.findAll();
    }

    public FichaMedica buscarPorId(Integer id) {
        return fichaMedicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ficha médica no encontrada"));
    }

    public FichaMedica buscarPorPacienteId(Integer pacienteId) {
        if (!pacienteRepository.existsById(pacienteId))
            throw new RuntimeException("Paciente no encontrado");
        return fichaMedicaRepository.findByPacienteId(pacienteId)
                .orElseThrow(() -> new RuntimeException("No se encontró ficha médica para ese paciente"));
    }

    public List<FichaMedica> buscarPorGrupoSanguineo(String grupoSanguineo) {
        List<FichaMedica> fichas = fichaMedicaRepository.findByGrupoSanguineo(grupoSanguineo);
        if (fichas.isEmpty())
            throw new RuntimeException("No se encontraron fichas con ese grupo sanguíneo");
        return fichas;
    }

    public FichaMedica guardar(FichaMedica fichaMedica) {
        if (fichaMedica.getId() != null)
            throw new RuntimeException("No se debe enviar un ID al crear una ficha médica");
        pacienteRepository.findById(fichaMedica.getPaciente().getId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        fichaMedicaRepository.findByPacienteId(fichaMedica.getPaciente().getId()).ifPresent(f -> {
            throw new RuntimeException("Ya existe una ficha médica para ese paciente");
        });
        return fichaMedicaRepository.save(fichaMedica);
    }

    public FichaMedica modificar(Integer id, FichaMedica datos) {
        if (!fichaMedicaRepository.existsById(id))
            throw new RuntimeException("Ficha médica no encontrada");
        if (datos.getPaciente() != null && datos.getPaciente().getId() != null
                && !pacienteRepository.existsById(datos.getPaciente().getId()))
            throw new RuntimeException("Paciente no encontrado");
        FichaMedica existente = fichaMedicaRepository.getReferenceById(id);
        Optional.ofNullable(datos.getPaciente()).ifPresent(existente::setPaciente);
        Optional.ofNullable(datos.getGrupoSanguineo()).ifPresent(existente::setGrupoSanguineo);
        Optional.ofNullable(datos.getAlergias()).ifPresent(existente::setAlergias);
        Optional.ofNullable(datos.getEnfermedadesCronicas()).ifPresent(existente::setEnfermedadesCronicas);
        Optional.ofNullable(datos.getPeso()).ifPresent(existente::setPeso);
        Optional.ofNullable(datos.getAltura()).ifPresent(existente::setAltura);
        Optional.ofNullable(datos.getObservaciones()).ifPresent(existente::setObservaciones);
        return fichaMedicaRepository.save(existente);
    }

    public void eliminar(Integer id) {
        if (!fichaMedicaRepository.existsById(id))
            throw new RuntimeException("Ficha médica no encontrada");
        fichaMedicaRepository.deleteById(id);
    }
}
