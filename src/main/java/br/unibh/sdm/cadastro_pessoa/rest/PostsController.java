package br.unibh.sdm.cadastro_pessoa.rest;

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

import br.unibh.sdm.cadastro_pessoa.entidades.Post;
import br.unibh.sdm.cadastro_pessoa.negocio.PostsServices;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "postagens")
public class PostsController {
   
    private final PostsServices postsServices;

    public PostsController(PostsServices postsServices){
        this.postsServices=postsServices;
    }

    @GetMapping
    public List<Post> getPost(){
        return postsServices.getPosts();
    }
    
    @GetMapping(value="{id}")
    public Post getPostById(@PathVariable String id) throws Exception{
        if(!ObjectUtils.isEmpty(id)){
           return postsServices.getPostById(id);
        }
        throw new Exception("Serviço com codigo "+id+" nao encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Post createPost(@RequestBody @NotNull Post post) throws Exception {
         return postsServices.savePost(post);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Post updatePost(@PathVariable String id, 
    		@RequestBody @NotNull Post post) throws Exception {
    	if (!id.equals(post.getId())) {
    		throw new Exception("Codigo "+id+" nao está correto");
    	}
    	if (!postsServices.isPostExists(post)) {
    		throw new Exception("Postagem com codigo "+id+" não existe");
    	}
        return postsServices.savePost(post);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean updatePost(@PathVariable String id) throws Exception {
    	if (!postsServices.isPostExists(id)) {
    		throw new Exception("Postagem com codigo "+id+" não existe");
    	} 
    	postsServices.deletePost(id);
        return true;
    }
    
}
