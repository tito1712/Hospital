package com.example.Hospital.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Hospital.Model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    Optional<Medico> findByRunMedico(Integer runMedico);

    List<Medico> findByNombre(String nombre);

    List<Medico> findByApellido(String apellido);

    List<Medico> findByNombreAndApellido(String nombre, String apellido);

    List<Medico> findByEspecialidad(String especialidad);

}
