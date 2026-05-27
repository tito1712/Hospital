package com.example.Hospital.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Hospital.Model.Citamedica;

public interface CitamedicaRepository extends JpaRepository<Citamedica, Integer> {

    List<Citamedica> findByPacienteId(Integer pacienteId);

    List<Citamedica> findByMedicoId(Integer medicoId);

    List<Citamedica> findByFecha(LocalDate fecha);

    List<Citamedica> findByHora(LocalTime hora);

    List<Citamedica> findByPacienteIdAndMedicoId(Integer pacienteId, Integer medicoId);

    List<Citamedica> findByFechaBetween(LocalDate inicio, LocalDate fin);

}
