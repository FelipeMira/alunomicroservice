package com.fiap.grupo.dois.steps;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fiap.grupo.dois.cucumber.CucumberRoot;
import com.fiap.grupo.dois.model.Aluno;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetAlunoStep extends CucumberRoot{
	
	private ResponseEntity<String> response;
	@SuppressWarnings("unused")
	private List<Aluno> responseObject;
	
	@SuppressWarnings("unchecked")
	@When("^O cliente chama a api /aluno$")
	public void o_cliente_chama_a_api_aluno() throws Throwable {
		Aluno aluno = new Aluno();
		aluno.update("José", "jose@email.com", "(11) 98745-5676");
		
		template.postForObject("/api/aluno", aluno, Aluno.class);
		response = template.getForEntity("/api/aluno", String.class);
		responseObject = template.getForObject("/api/aluno", List.class);
		
		
	}

	@Then("^Valido o codigo de retorno (\\d+)$")
	public void valido_o_codigo_de_retorno(int statusCode) throws Throwable {
		HttpStatus currentStatusCode = response.getStatusCode();
        assertThat("status code is incorrect : " +
                response.getBody(), currentStatusCode.value(), is(statusCode));
	}
}
