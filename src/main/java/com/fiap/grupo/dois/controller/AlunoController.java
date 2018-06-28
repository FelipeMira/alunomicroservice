package com.fiap.grupo.dois.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.grupo.dois.dto.AlunoDTO;
import com.fiap.grupo.dois.error.AlunoNotFoundException;
import com.fiap.grupo.dois.service.AlunoService;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
	
	private final AlunoService service;
	 
    @Autowired
    AlunoController(AlunoService service) {
        this.service = service;
    }
 
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    AlunoDTO create(@RequestBody @Valid AlunoDTO alunoEntry) {
        return service.create(alunoEntry);
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    AlunoDTO delete(@PathVariable("id") String id) {
        return service.delete(id);
    }
 
    @RequestMapping(method = RequestMethod.GET)
    List<AlunoDTO> findAll() {
        return service.findAll();
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    AlunoDTO findById(@PathVariable("id") String id) {
        return service.findById(id);
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    AlunoDTO update(@RequestBody @Valid AlunoDTO alunoEntry) {
        return service.update(alunoEntry);
    }
 
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoNotFound(AlunoNotFoundException ex) {
    }
}
