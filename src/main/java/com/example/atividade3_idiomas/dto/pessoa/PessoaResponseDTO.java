package com.example.atividade3_idiomas.dto.pessoa;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PessoaResponseDTO {
    private UUID id;
    private String nome;
    private String email;
    private int idade;
    private List<String> idiomas;
}
