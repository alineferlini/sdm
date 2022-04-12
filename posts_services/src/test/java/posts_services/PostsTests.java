package posts_services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import br.unibh.sdm.posts_services.entidades.Post;
import br.unibh.sdm.posts_services.persistencia.PostRepository;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, PostsTests.DynamoDBConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostsTests {

    private static Logger LOGGER = LoggerFactory.getLogger(PostsTests.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	    
    @Configuration
	@EnableDynamoDBRepositories(basePackageClasses = PostRepository.class)
	public static class DynamoDBConfig {

		@Value("${amazon.aws.accesskey}")
		private String amazonAWSAccessKey;

		@Value("${amazon.aws.secretkey}")
		private String amazonAWSSecretKey;

		public AWSCredentialsProvider amazonAWSCredentialsProvider() {
			return new AWSStaticCredentialsProvider(amazonAWSCredentials());
		}

		@Bean
		public AWSCredentials amazonAWSCredentials() {
			return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
		}

		@Bean
		public AmazonDynamoDB amazonDynamoDB() {
			return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
					.withRegion(Regions.US_EAST_1).build();
		}
	}
    
	@Autowired
	private PostRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");
		Post c1 = new Post("123", df.parse("11/04/2022"), "Maria", "descricao", "linkimagem", "BeloHorizonte", "minas", "3456");
		repository.save(c1);
		
		Post c2 = new Post("321", df.parse("11/04/2022"), "Joao", "descricao", "linkimagem", "BeloHorizonte", "minas", "3456");
		repository.save(c2);
		
		Post c3 = new Post("567", df.parse("11/04/2022"), "Jose", "descricao", "linkimagem", "BeloHorizonte", "minas", "3456");
		repository.save(c3);
		

		LOGGER.info("Pesquisado todos");
		Iterable<Post> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Post post : lista) {
			LOGGER.info(post.toString());
		}
		LOGGER.info("Pesquisado um objeto");
		List<Post> result = repository.findByNome("Joao");
		assertEquals(result.size(), 1);
		assertEquals(result.get(0).getId(), "321");
		LOGGER.info("Encontrado: {}", result.get(0));
	}
	
	
	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		Post c1 = new Post("123", df.parse("11/04/2022"), "Maria", "descricao", "linkimagem", "BeloHorizonte", "minas", "3456");
		repository.delete(c1);
		
		Post c2 = new Post("321", df.parse("11/04/2022"), "Joao", "descricao", "linkimagem", "BeloHorizonte", "minas", "3456");
		repository.delete(c2);
		
		Post c3 = new Post("567", df.parse("11/04/2022"), "Jose", "descricao", "linkimagem", "BeloHorizonte", "minas", "3456");
		repository.delete(c3);
		
		List<Post> result = repository.findByNome("Maria");
		assertEquals(result.size(), 0);
		LOGGER.info("Exclusão feita com sucesso");
	}
	
	
}