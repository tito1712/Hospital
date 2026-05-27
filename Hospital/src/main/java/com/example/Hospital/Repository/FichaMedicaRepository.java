package com.example.Hospital.Repository;

import com.example.Hospital.Model.FichaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FichaMedicaRepository extends JpaRepository<FichaMedica, Integer> {

    Optional<FichaMedica> findByPacienteId(Integer pacienteId);

    List<FichaMedica> findByGrupoSanguineo(String grupoSanguineo);
}
