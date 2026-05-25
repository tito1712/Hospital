package com.example.Hospital.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "run_paciente")
    private Integer runPaciente;
    private String nombre;
    private String apellido;
    @Column(unique = true)
    private String correo;
    private String telefono;
    private String direccion;
    private String fecha_nacimiento;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private List<Citamedica> citamedicas;
}
