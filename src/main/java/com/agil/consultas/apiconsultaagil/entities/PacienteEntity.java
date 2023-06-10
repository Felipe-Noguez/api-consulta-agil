package com.agil.consultas.apiconsultaagil.entities;

import com.agil.consultas.apiconsultaagil.entities.pk.PacienteConsultaPK;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "paciente")
public class PacienteEntity {

    @Id
    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone", unique = true, nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "pacienteEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PacienteConsultaPK> pacienteConsultaPK;
}
