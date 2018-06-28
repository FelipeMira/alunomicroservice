package com.fiap.grupo.dois.mongoDb;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.grupo.dois.dto.AlunoDTO;
import com.fiap.grupo.dois.error.AlunoNotFoundException;
import com.fiap.grupo.dois.model.Aluno;
import com.fiap.grupo.dois.repository.AlunoRepository;
import com.fiap.grupo.dois.service.AlunoService;

@Service
public final class MongoDBAlunoService implements AlunoService{
	
	private final AlunoRepository repository;
	 
    @Autowired
    MongoDBAlunoService(AlunoRepository repository) {
        this.repository = repository;
    }
 
    @Override
    public AlunoDTO create(AlunoDTO aluno) {
        Aluno persisted = Aluno.getBuilder()
                .nome(aluno.getNome())
                .email(aluno.getEmail())
                .telefone(aluno.getTelefone())
                .build();
        persisted = repository.save(persisted);
        return convertToDTO(persisted);
    }
 
    @Override
    public AlunoDTO delete(String id) {
        Aluno deleted = findAlunoById(id);
        repository.delete(deleted);
        return convertToDTO(deleted);
    }
 
    @Override
    public List<AlunoDTO> findAll() {
        List<Aluno> alunoEntries = repository.findAll();
        return convertToDTOs(alunoEntries);
    }
 
    private List<AlunoDTO> convertToDTOs(List<Aluno> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }
 
    @Override
    public AlunoDTO findById(String id) {
        Aluno found = findAlunoById(id);
        return convertToDTO(found);
    }
 
    @Override
    public AlunoDTO update(AlunoDTO aluno) {
        Aluno updated = findAlunoById(aluno.getId());
        updated.update(aluno.getNome(), aluno.getEmail(), aluno.getTelefone());
        updated = repository.save(updated);
        return convertToDTO(updated);
    }
 
    private Aluno findAlunoById(String id) {
        Optional<Aluno> result = repository.findById(id);
        return result.orElseThrow(() -> new AlunoNotFoundException(id));
 
    }
 
    private AlunoDTO convertToDTO(Aluno model) {
        AlunoDTO dto = new AlunoDTO();
 
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setEmail(model.getEmail());
        dto.setTelefone(model.getTelefone());
 
        return dto;
    }
}
