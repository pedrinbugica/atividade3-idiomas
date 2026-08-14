package com.example.atividade3_idiomas.dto.idioma;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class IdiomaResponseDTO {
    private UUID id;
    private String nome;
    private List<String> pessoas;
}
