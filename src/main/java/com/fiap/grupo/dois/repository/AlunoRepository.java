package com.fiap.grupo.dois.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.fiap.grupo.dois.model.Aluno;

public interface AlunoRepository extends Repository<Aluno, String> {
	 
    void delete(Aluno deleted);
    
    List<Aluno> findAll();
 
    Optional<Aluno> findById(String id);
 
    Aluno save(Aluno saved);
}
