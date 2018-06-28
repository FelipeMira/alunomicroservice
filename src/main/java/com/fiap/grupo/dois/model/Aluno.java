package com.fiap.grupo.dois.model;

import static util.PreCondition.isTrue;
import static util.PreCondition.notEmpty;
import static util.PreCondition.notNull;

import org.springframework.data.annotation.Id;

public final class Aluno {

    public static final int MAX_LENGTH_EMAIL = 500;
    public static final int MAX_LENGTH_NOME = 100;
    public static final int MAX_LENGTH_TELEPHONE = 15;
    
	@Id
    private String id;
	private String nome;
	private String email;
	private String telefone;
	
	public Aluno() {}
	
	public Aluno(Builder builder) {
		super();
		this.nome = builder.nome;
		this.email = builder.email;
		this.telefone = builder.telefone;
	}
	
	public static Builder getBuilder() {
        return new Builder();
    }

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}


	public String getTelefone() {
		return telefone;
	}
	
   public void update(String nome, String email, String telefone) {
	   checkObject(nome, email, telefone);

        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }


	/**
     * We don't have to use the builder pattern here because the constructed class has only two String fields.
     * However, I use the builder pattern in this example because it makes the code a bit easier to read.
     */
    public static class Builder {

        private String nome;
        private String email;
        private String telefone;

        private Builder() {}

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public Builder telefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public Aluno build() {
            Aluno build = new Aluno(this);

            build.checkObject(build.getNome(), build.getEmail(), build.getTelefone());

            return build;
        }
    }

    private void checkObject(String nome, String email, String telefone) {
        notNull(nome, "Nome não pode ser nulo");
        notEmpty(nome, "Nome não pode estar vazio");
        isTrue(nome.length() <= MAX_LENGTH_NOME,
                "Nome cannot be longer than %d characters",
                MAX_LENGTH_NOME
        );

        if (email != null) {
            isTrue(email.length() <= MAX_LENGTH_EMAIL,
                    "Email não pode ter mais que %d characters",
                    MAX_LENGTH_EMAIL
            );
        }
        
        if (telefone != null) {
            isTrue(telefone.length() <= MAX_LENGTH_TELEPHONE,
                    "Telefone não pode ter mais que %d characters",
                    MAX_LENGTH_TELEPHONE
            );
        }
    }
}
