package com.example.Hospital.Repository;

import com.example.Hospital.Model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

    List<Medicamento> findByNombre(String nombre);

    List<Medicamento> findByPacienteId(Integer pacienteId);

    List<Medicamento> findByMedicoId(Integer medicoId);
}
