package com.example.atividade3_idiomas.Service;

import com.example.atividade3_idiomas.repository.IIdiomaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdiomaService {
    private final IIdiomaRepository idiomaRepository;


}