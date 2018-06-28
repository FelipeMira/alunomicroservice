package com.fiap.grupo.dois.service;

import java.util.List;

import com.fiap.grupo.dois.dto.AlunoDTO;

public interface AlunoService {
	
	AlunoDTO create(AlunoDTO aluno);
	 
	AlunoDTO delete(String id);
 
    List<AlunoDTO> findAll();
 
    AlunoDTO findById(String id);
 
    AlunoDTO update(AlunoDTO aluno);
}
