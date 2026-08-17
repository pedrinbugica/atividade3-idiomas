package com.example.atividade3_idiomas.Service;

import com.example.atividade3_idiomas.dto.idioma.IdiomaRequestDTO;
import com.example.atividade3_idiomas.dto.idioma.IdiomaResponseDTO;
import com.example.atividade3_idiomas.model.IdiomaModel;
import com.example.atividade3_idiomas.model.PessoaModel;
import com.example.atividade3_idiomas.repository.IIdiomaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IdiomaService {
    private final IIdiomaRepository idiomaRepository;

    public IdiomaResponseDTO toResponseDTO(IdiomaModel idioma) {
        IdiomaResponseDTO dto = new IdiomaResponseDTO();
        dto.setId(idioma.getId());
        dto.setNome(idioma.getNome());
        return dto;
    }

    public IdiomaResponseDTO cadastrar(IdiomaRequestDTO idiomaRequestDTO) {
        if (idiomaRepository.existsByNome(idiomaRequestDTO.getNome())) {
            throw new  IllegalArgumentException("Idoma ja cadastrado");
        }
        IdiomaModel idiomaModel = new IdiomaModel();
        idiomaModel.setNome(idiomaRequestDTO.getNome());

        var idiomaSalvo = idiomaRepository.save(idiomaModel);

        IdiomaResponseDTO idiomaResponseDTO = new IdiomaResponseDTO();
        idiomaResponseDTO.setId(idiomaSalvo.getId());
        idiomaResponseDTO.setNome(idiomaSalvo.getNome());
        return idiomaResponseDTO;
    }

    public List<IdiomaResponseDTO> listar() {
        var lista = idiomaRepository.findAll();
        return lista.stream().map(this::toResponseDTO).toList();
    }

    public IdiomaResponseDTO buscarPorId(UUID id) {
        IdiomaModel idiomaModel = idiomaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Idioma não encontrado"));
        return toResponseDTO(idiomaModel);
    }

    public IdiomaResponseDTO alterar(UUID id,IdiomaRequestDTO idiomaRequestDTO) {
        IdiomaModel idiomaModel = idiomaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Idioma nao encontrado"));

        idiomaModel.setNome(idiomaRequestDTO.getNome());
        var idiomaSalvo = idiomaRepository.save(idiomaModel);
        return toResponseDTO(idiomaSalvo);
    }

    public void excluir(UUID id) {
        IdiomaModel idiomaModel = idiomaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Idioma nao encontrado"));
        if (!idiomaModel.getPessoa().isEmpty()) {
            throw new RuntimeException("O idioma está associado a pessoas e não pode ser excluído");

        }
        idiomaRepository.delete(idiomaModel);
    }

    public List<String> listarPessoasDoIdioma(UUID id) {
        IdiomaModel idioma = idiomaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("idioma não existe"));

        return idioma.getPessoa().stream().map(PessoaModel::getNome).toList();

    }
}