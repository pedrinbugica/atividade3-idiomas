package com.example.atividade3_idiomas.controller;

import com.example.atividade3_idiomas.Service.PessoaService;
import com.example.atividade3_idiomas.dto.pessoa.PessoaRequestDTO;
import com.example.atividade3_idiomas.dto.pessoa.PessoaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class ControllerPessoa {
    private final PessoaService pessoaService;

    @PostMapping
    public PessoaResponseDTO cadastrar(@RequestBody PessoaRequestDTO pessoaRequestDTO) {
        return pessoaService.cadastrar(pessoaRequestDTO);
    }

    @GetMapping
    public List<PessoaResponseDTO> listar() {
        return pessoaService.listar();
    }

    @GetMapping("/{id}")
    public PessoaResponseDTO buscarPorId(@PathVariable UUID id) {
        return pessoaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public PessoaResponseDTO alterar(@PathVariable UUID id, @RequestBody PessoaRequestDTO pessoaRequestDTO){
        return pessoaService.alterar(id, pessoaRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable UUID id) {
        pessoaService.excluir(id);
    }

    @PostMapping("/{pessoaId}/idiomas/{idiomaId}")
    public PessoaResponseDTO associarIdioma(@PathVariable UUID pessoaId, @PathVariable UUID idiomaId) {
        return pessoaService.associarIdioma(pessoaId,idiomaId);
    }

    @DeleteMapping("/{pessoaId}/idiomas/{idiomaId}")
    public PessoaResponseDTO removerIdioma(@PathVariable UUID pessoaId, @PathVariable UUID idiomaId) {
        return pessoaService.removerIdioma(pessoaId,idiomaId);
    }

    @GetMapping("/{id}/idiomas")
    public List<String> listarIdiomas(@PathVariable UUID id) {
        return pessoaService.listarIdiomasDaPessoa(id);
    }
}
