package com.example.atividade3_idiomas.repository;

import com.example.atividade3_idiomas.model.IdiomaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IIdiomaRepository extends JpaRepository<IdiomaModel, UUID> {

    boolean existsByNome(String nome);
}
