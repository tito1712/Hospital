package com.example.Hospital.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Hospital.Model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    List<Paciente> findByNombre(String nombre);

    Optional<Paciente> findByCorreo(String correo);

    Optional<Paciente> findByRunPaciente(Integer run_paciente);

    Optional<Paciente> findByTelefono(String telefono);
}
