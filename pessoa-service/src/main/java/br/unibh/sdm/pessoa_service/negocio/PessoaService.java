package br.unibh.sdm.pessoa_service.negocio;


import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.pessoa_service.entidades.Pessoa;
import br.unibh.sdm.pessoa_service.persistencia.PessoaRepository;



@Service
public class PessoaService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    private final PessoaRepository postRepo;

    public PessoaService(PessoaRepository PessoaRepository){
        this.postRepo=PessoaRepository;
    }
    
    public List<Pessoa> getPessoas(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Pessoa> lista = this.postRepo.findAll();
        if (lista == null) {
        	return new ArrayList<Pessoa>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public Pessoa getPessoaById(String id){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Pessoa com o codigo {}",id);
        }
        Optional<Pessoa> retorno = this.postRepo.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("Pessoa com o codigo "+id+" nao encontrada");
        }
        return retorno.get();
    }
    
    public Pessoa getPessoaByNome(String nome){
        if(logger.isInfoEnabled()){
            logger.info("Buscando serviços com o nome {}",nome);
        }
        List<Pessoa> lista = this.postRepo.findByNome(nome);
        if(lista == null || lista.isEmpty()){
            throw new RuntimeException("Serviços com o nome "+nome+" nao encontrada");
        }
        return lista.get(0);
    }

    public Pessoa savePessoa(Pessoa pessoa){
        if(logger.isInfoEnabled()){
            logger.info("Salvando modificações com os detalhes {}",pessoa.toString());
        }
        return this.postRepo.save(pessoa);
    }
    
    public void deletePessoa(String id){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo serviço com id {}",id);
        }
        this.postRepo.deleteById(id);
    }

    public boolean isPessoaExists(Pessoa pessoa){
    	Optional<Pessoa> retorno = this.postRepo.findById(pessoa.getId());
        return retorno.isPresent() ? true:  false;
    }

    public boolean isPessoaExists(String id){
    	Optional<Pessoa> retorno = this.postRepo.findById(id);
        return retorno.isPresent() ? true:  false;
    }
}
