package com.example.atividade3_idiomas.controller;

import com.example.atividade3_idiomas.Service.IdiomaService;
import com.example.atividade3_idiomas.dto.idioma.IdiomaRequestDTO;
import com.example.atividade3_idiomas.dto.idioma.IdiomaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/idiomas")
@RequiredArgsConstructor
public class IdiomaController {
    private final IdiomaService idiomaService;

    @PostMapping
    public IdiomaResponseDTO cadastrar(@RequestBody IdiomaRequestDTO idiomaRequestDTO) {
        return idiomaService.cadastrar(idiomaRequestDTO);
    }

    @GetMapping
    public List<IdiomaResponseDTO> listar() {
        return idiomaService.listar();
    }
}
