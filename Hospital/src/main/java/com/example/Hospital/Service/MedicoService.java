package com.example.Hospital.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Hospital.Model.Medico;
import com.example.Hospital.Repository.MedicoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    public List<Medico> listar() {
        return medicoRepository.findAll();
    }

    public Medico buscarPorId(Integer id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
    }

    public Medico buscarPorRun(Integer runMedico) {
        return medicoRepository.findByRunMedico(runMedico)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
    }

    public List<Medico> buscarPorNombre(String nombre) {
        List<Medico> medicos = medicoRepository.findByNombre(nombre);
        if (medicos.isEmpty())
            throw new RuntimeException("Medico no encontrado");
        return medicos;
    }

    public List<Medico> buscarPorApellido(String apellido) {
        List<Medico> medicos = medicoRepository.findByApellido(apellido);
        if (medicos.isEmpty())
            throw new RuntimeException("Medico no encontrado");
        return medicos;
    }

    public List<Medico> buscarPorNombreYApellido(String nombre, String apellido) {
        List<Medico> medicos = medicoRepository.findByNombreAndApellido(nombre, apellido);
        if (medicos.isEmpty())
            throw new RuntimeException("Medico no encontrado");
        return medicos;
    }

    public List<Medico> buscarPorEspecialidad(String especialidad) {
        List<Medico> medicos = medicoRepository.findByEspecialidad(especialidad);
        if (medicos.isEmpty())
            throw new RuntimeException("Medico no encontrado");
        return medicos;
    }

    public Medico guardar(Medico medico) {
        medicoRepository.findByRunMedico(medico.getRunMedico()).ifPresent(m -> {
            throw new RuntimeException("Ya existe un medico con ese RUN");
        });
        return medicoRepository.save(medico);
    }

    public Medico modificar(Integer id, Medico datos) {
        if (!medicoRepository.existsById(id))
            throw new RuntimeException("Medico no encontrado");
        Medico existente = medicoRepository.getReferenceById(id);
        Optional.ofNullable(datos.getRunMedico()).ifPresent(existente::setRunMedico);
        Optional.ofNullable(datos.getNombre()).ifPresent(existente::setNombre);
        Optional.ofNullable(datos.getApellido()).ifPresent(existente::setApellido);
        Optional.ofNullable(datos.getEspecialidad()).ifPresent(existente::setEspecialidad);
        Optional.ofNullable(datos.getJefe_turno()).ifPresent(existente::setJefe_turno);
        Optional.ofNullable(datos.getCorreo()).ifPresent(existente::setCorreo);
        return medicoRepository.save(existente);
    }

    public void eliminar(Integer id) {
        if (!medicoRepository.existsById(id))
            throw new RuntimeException("Medico no encontrado");
        medicoRepository.deleteById(id);
    }

}
