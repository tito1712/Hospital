package com.example.Hospital.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ficha_medica")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(name = "grupo_sanguineo", nullable = false)
    private String grupoSanguineo;

    @Column(name = "alergias")
    private String alergias;

    @Column(name = "enfermedades_cronicas")
    private String enfermedadesCronicas;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "observaciones")
    private String observaciones;
}
