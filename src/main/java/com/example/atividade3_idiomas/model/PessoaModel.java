package com.example.atividade3_idiomas.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_pessoa")
public class PessoaModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String nome;
    private  String email;
    private int idade;

    @ManyToMany
    @JoinTable(
            name = "pessoa_idioma",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "idioma_id")
    )
    private List<IdiomaModel> idiomaModels = new ArrayList<>();

}
