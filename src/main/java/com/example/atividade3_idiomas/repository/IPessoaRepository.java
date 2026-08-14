package com.example.atividade3_idiomas.repository;

import com.example.atividade3_idiomas.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPessoaRepository extends JpaRepository<PessoaModel, UUID> {
}
