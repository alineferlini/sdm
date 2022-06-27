package br.unibh.sdm.pessoa_service.persistencia;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.pessoa_service.entidades.Pessoa;

@EnableScan()
public interface PessoaRepository extends CrudRepository<Pessoa, String> {
	List<Pessoa>	findByNome(String nome);
}
