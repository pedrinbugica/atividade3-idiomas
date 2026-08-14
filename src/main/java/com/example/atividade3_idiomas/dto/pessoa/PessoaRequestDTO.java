package com.example.atividade3_idiomas.dto.pessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaRequestDTO {
    private String nome;
    private String email;
    private int idade;
}
