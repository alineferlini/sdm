package br.unibh.sdm.posts_services.negocio;


import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.posts_services.entidades.Post;
import br.unibh.sdm.posts_services.persistencia.PostRepository;


@Service
public class PostsServices {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    private final PostRepository postRepo;

    public PostsServices(PostRepository PostRepository){
        this.postRepo=PostRepository;
    }
    
    public List<Post> getPosts(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Post> lista = this.postRepo.findAll();
        if (lista == null) {
        	return new ArrayList<Post>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public Post getPostById(String id){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Post com o codigo {}",id);
        }
        Optional<Post> retorno = this.postRepo.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("Post com o codigo "+id+" nao encontrada");
        }
        return retorno.get();
    }
    
    public Post getPostByNome(String nome){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Criptomoeda com o nome {}",nome);
        }
        List<Post> lista = this.postRepo.findByNome(nome);
        if(lista == null || lista.isEmpty()){
            throw new RuntimeException("Criptomoeda com o nome "+nome+" nao encontrada");
        }
        return lista.get(0);
    }

    public Post savePost(Post post){
        if(logger.isInfoEnabled()){
            logger.info("Salvando Criptomoeda com os detalhes {}",post.toString());
        }
        return this.postRepo.save(post);
    }
    
    public void deletePost(String id){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo Criptomoeda com id {}",id);
        }
        this.postRepo.deleteById(id);
    }

    public boolean isPostExists(Post post){
    	Optional<Post> retorno = this.postRepo.findById(post.getId());
        return retorno.isPresent() ? true:  false;
    }

    public boolean isPostExists(String id){
    	Optional<Post> retorno = this.postRepo.findById(id);
        return retorno.isPresent() ? true:  false;
    }
}
