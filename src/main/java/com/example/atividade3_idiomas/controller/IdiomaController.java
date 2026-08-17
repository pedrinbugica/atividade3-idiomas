package com.example.atividade3_idiomas.controller;

import com.example.atividade3_idiomas.Service.IdiomaService;
import com.example.atividade3_idiomas.dto.idioma.IdiomaRequestDTO;
import com.example.atividade3_idiomas.dto.idioma.IdiomaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public IdiomaResponseDTO buscarPorId(@PathVariable UUID id) {
        return idiomaService.buscarPorId(id);
    }
    @PutMapping("/{id}")
    public IdiomaResponseDTO alterar(@RequestBody IdiomaRequestDTO idiomaRequestDTO, @PathVariable UUID id) {
        return idiomaService.alterar(id, idiomaRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable UUID id) {
        idiomaService.excluir(id);
    }
    @GetMapping("/{id}/pessoas")
    public List<String> listarPessoas(@PathVariable UUID id) {
      return idiomaService.listarPessoasDoIdioma(id);
    }
}

