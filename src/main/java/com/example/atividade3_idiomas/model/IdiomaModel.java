package com.example.atividade3_idiomas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_idiomas")
public class IdiomaModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String nome;

    @ManyToMany(mappedBy = "idiomaModels")
    private List<PessoaModel> pessoa;

}
