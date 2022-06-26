package br.unibh.sdm.cadastro_pessoa.persistencia;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.cadastro_pessoa.entidades.Post;

@EnableScan()
public interface PostRepository extends CrudRepository<Post, String> {
	List<Post>	findByNome(String nome);
}
