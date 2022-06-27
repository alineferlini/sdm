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

import br.unibh.sdm.pessoa_service.entidades.Pessoa;
import br.unibh.sdm.pessoa_service.persistencia.PessoaRepository;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, PessooaTest.DynamoDBConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PessooaTest {

    private static Logger LOGGER = LoggerFactory.getLogger(PessooaTest.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	    
    @Configuration
	@EnableDynamoDBRepositories(basePackageClasses = PessoaRepository.class)
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
	private PessoaRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");
		Pessoa c1 = new Pessoa(null, "12312312312", "TESTE Maria", "bh", "mg", "12312312312");
		repository.save(c1);
		
		Pessoa c2 = new Pessoa(null, "12312312311", "TESTE joao", "bh", "mg", "45645645645");
		repository.save(c2);
		
		Pessoa c3 = new Pessoa(null, "12312312310", "TESTE josé", "bh", "mg", "78978978978");
		repository.save(c3);
		

		LOGGER.info("Pesquisado todos");
		Iterable<Pessoa> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Pessoa pessoa : lista) {
			LOGGER.info(pessoa.toString());
		}
		LOGGER.info("Pesquisado um objeto");
		List<Pessoa> result = repository.findByNome("TESTE joao");
		assertEquals(result.size(), 1);
		assertEquals(result.get(0).getCidade(), "bh");
		LOGGER.info("Encontrado: {}", result.get(0));
	}
	
	
	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Criando objetos...");
		
		
		List<Pessoa> result = repository.findByNome("TESTE Maria");
		repository.delete(result.get(0));
		result = repository.findByNome("TESTE joao");
		repository.delete(result.get(0));
		result = repository.findByNome("TESTE josé");
		repository.delete(result.get(0));
		
		result = repository.findByNome("TESTE josé");
		assertEquals(result.size(), 0);
		LOGGER.info("Exclusão feita com sucesso");
	}
	
	
}