package com.fiap.grupo.dois.error;

@SuppressWarnings("serial")
public class AlunoNotFoundException extends RuntimeException {

    public AlunoNotFoundException(String id) {
        super(String.format("Nenhum aluno encontrado pelo id: <%s>", id));
    }
}
