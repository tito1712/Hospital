package com.example.Hospital.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Hospital.Exception.RecursoNoEncontradoException;
import com.example.Hospital.Model.Paciente;
import com.example.Hospital.Repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public List<Paciente> buscarPorNombre(String nombre) {
        List<Paciente> pacientes = pacienteRepository.findByNombre(nombre);
        if (pacientes.isEmpty())
            throw new RecursoNoEncontradoException("Paciente no encontrado");
        return pacientes;
    }

    public Paciente buscarPorCorreo(String correo) {
        return pacienteRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public Paciente buscarPorTelefono(String telefono) {
        return pacienteRepository.findByTelefono(telefono)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public Paciente guardar(Paciente paciente) {
        if (paciente.getId() != null)
            throw new RuntimeException("No se debe enviar un ID al crear un paciente");
        return pacienteRepository.save(paciente);
    }

    public Paciente buscarPorId(Integer id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public Paciente modificar(Integer id, Paciente datos) {
        if (!pacienteRepository.existsById(id))
            throw new RecursoNoEncontradoException("Paciente no encontrado");
        Paciente existente = pacienteRepository.getReferenceById(id);
        Optional.ofNullable(datos.getNombre()).ifPresent(existente::setNombre);
        Optional.ofNullable(datos.getApellido()).ifPresent(existente::setApellido);
        Optional.ofNullable(datos.getCorreo()).ifPresent(existente::setCorreo);
        Optional.ofNullable(datos.getTelefono()).ifPresent(existente::setTelefono);
        Optional.ofNullable(datos.getDireccion()).ifPresent(existente::setDireccion);
        Optional.ofNullable(datos.getFechaNacimiento()).ifPresent(existente::setFechaNacimiento);
        return pacienteRepository.save(existente);
    }

    public void eliminar(Integer id) {
        if (!pacienteRepository.existsById(id))
            throw new RecursoNoEncontradoException("Paciente no encontrado");
        pacienteRepository.deleteById(id);
    }

}
