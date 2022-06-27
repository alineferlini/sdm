package br.unibh.sdm.pessoa_service.rest;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.unibh.sdm.pessoa_service.entidades.Pessoa;
import br.unibh.sdm.pessoa_service.negocio.PessoaService;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "postagens")
public class PessoaController {
   
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService){
        this.pessoaService=pessoaService;
    }

    @GetMapping
    public List<Pessoa> getPessoa(){
        return pessoaService.getPessoas();
    }
    
    @GetMapping(value="{id}")
    public Pessoa getPessoaById(@PathVariable String id) throws Exception{
        if(!ObjectUtils.isEmpty(id)){
           return pessoaService.getPessoaById(id);
        }
        throw new Exception("Serviço com codigo "+id+" nao encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa createPessoa(@RequestBody @NotNull Pessoa pessoa) throws Exception {
         return pessoaService.savePessoa(pessoa);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa updatePessoa(@PathVariable String id, 
    		@RequestBody @NotNull Pessoa pessoa) throws Exception {
    	if (!id.equals(pessoa.getId())) {
    		throw new Exception("Codigo "+id+" nao está correto");
    	}
    	if (!pessoaService.isPessoaExists(pessoa)) {
    		throw new Exception("Pessoaagem com codigo "+id+" não existe");
    	}
        return pessoaService.savePessoa(pessoa);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean updatePessoa(@PathVariable String id) throws Exception {
    	if (!pessoaService.isPessoaExists(id)) {
    		throw new Exception("Pessoaagem com codigo "+id+" não existe");
    	} 
    	pessoaService.deletePessoa(id);
        return true;
    }
    
}
