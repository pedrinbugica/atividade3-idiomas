package com.example.atividade3_idiomas.Service;

import com.example.atividade3_idiomas.dto.pessoa.PessoaRequestDTO;
import com.example.atividade3_idiomas.dto.pessoa.PessoaResponseDTO;
import com.example.atividade3_idiomas.model.IdiomaModel;
import com.example.atividade3_idiomas.model.PessoaModel;
import com.example.atividade3_idiomas.repository.IIdiomaRepository;
import com.example.atividade3_idiomas.repository.IPessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PessoaService {
    private final IPessoaRepository iPessoaRepository;
    private final IIdiomaRepository idiomaRepository;

    public PessoaResponseDTO toResponseDTO(PessoaModel pessoa) {
        PessoaResponseDTO dto = new PessoaResponseDTO();
        dto.setId(pessoa.getId());
        dto.setNome(pessoa.getNome());
        dto.setEmail(pessoa.getEmail());
        dto.setIdade(pessoa.getIdade());

        List<String> nomeIdiomas = pessoa.getIdiomaModels()
                .stream().map(IdiomaModel::getNome).toList();

        dto.setIdiomas(nomeIdiomas);
        return dto;
    }

    public PessoaResponseDTO cadastrar(PessoaRequestDTO pessoaRequestDTO) {
        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setNome(pessoaRequestDTO.getNome());
        pessoaModel.setEmail(pessoaRequestDTO.getEmail());
        pessoaModel.setIdade(pessoaRequestDTO.getIdade());

        var pessoaSalva = iPessoaRepository.save(pessoaModel);
        return toResponseDTO(pessoaSalva);
    }

    public List<PessoaResponseDTO> listar() {
        var lista = iPessoaRepository.findAll();
        return lista.stream().map(this::toResponseDTO).toList();
    }

    public PessoaResponseDTO buscarPorId(UUID id) {
        PessoaModel pessoaModel = iPessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrado"));
        return toResponseDTO(pessoaModel);
    }

    public PessoaResponseDTO alterar(UUID id, PessoaRequestDTO pessoaRequestDTO) {
        PessoaModel pessoaModel = iPessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa nao encontrada"));

        pessoaModel.setNome(pessoaRequestDTO.getNome());
        pessoaModel.setEmail(pessoaRequestDTO.getEmail());
        pessoaModel.setIdade(pessoaRequestDTO.getIdade());

        var pessoaSalva = iPessoaRepository.save(pessoaModel);
        return toResponseDTO(pessoaSalva);
    }

    public void excluir(UUID id) {
        PessoaModel pessoaModel = iPessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa nao encontrada"));
        iPessoaRepository.delete(pessoaModel);
    }

    public PessoaResponseDTO associarIdioma(UUID id, UUID idiomaId) {
        PessoaModel pessoaModel = iPessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa nao encontrada"));

        IdiomaModel idiomaModel = idiomaRepository.findById(idiomaId)
                .orElseThrow(() -> new IllegalArgumentException("Idioma nao existe"));

        if (pessoaModel.getIdiomaModels().contains(idiomaModel)) {
            throw new IllegalArgumentException("Pessoa já possui esse idioma");
        }
        pessoaModel.getIdiomaModels().add(idiomaModel);
        PessoaModel pessoaAtt = iPessoaRepository.save(pessoaModel);
        return toResponseDTO(pessoaAtt);
    }

    public PessoaResponseDTO removerIdioma(UUID id, UUID idiomaId) {
        PessoaModel pessoa = iPessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não existe"));

        IdiomaModel idioma = idiomaRepository.findById(idiomaId)
                .orElseThrow(() -> new RuntimeException("Idioma não existe"));

        if (!pessoa.getIdiomaModels().contains(idioma)) {
            throw new RuntimeException("Idioma não vinculado a Pessoa");
        }
        pessoa.getIdiomaModels().remove(idioma);
        PessoaModel atualizado = iPessoaRepository.save(pessoa);
        return toResponseDTO(atualizado);
    }
    public List<String> listarIdiomasDaPessoa(UUID id) {
        PessoaModel pessoa = iPessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não existe"));

       return pessoa.getIdiomaModels().stream().map(IdiomaModel::getNome).toList();

    }
}
