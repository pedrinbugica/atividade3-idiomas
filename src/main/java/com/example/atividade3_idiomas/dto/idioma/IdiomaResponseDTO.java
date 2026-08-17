package com.example.atividade3_idiomas.dto.idioma;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdiomaResponseDTO {
    private UUID id;
    private String nome;
    private List<String> pessoas;


}
