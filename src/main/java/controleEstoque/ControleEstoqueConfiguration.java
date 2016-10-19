package controleEstoque;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe que configura os beans para persistencia
 * 
 * @author Usu�rio de Teste
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "controleEstoque-EntityManagerFactory", transactionManagerRef = "controleEstoque-TransactionManager")
class ControleEstoqueConfiguration {

	@Bean(name = "controleEstoque-EntityManagerFactory")
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("controleEstoque");
		return factoryBean;
	}

	@Bean(name = "controleEstoque-TransactionManager")
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}

	@Bean
	public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {

    //Criando dinamicamente os dados de venda
    String templateVenda = "";
		try {
			Scanner scanner = new Scanner(new File(getClass().getClassLoader().getResource("templateVenda.json").getFile()));
			templateVenda = scanner.useDelimiter("\\A").next();
			scanner.close(); 
			
			Calendar cal = Calendar.getInstance();
      cal.setTime(new Date());
			
			templateVenda = templateVenda.replaceAll(Pattern.quote("{{year}}"), String.valueOf(cal.get(Calendar.YEAR)));
			templateVenda = templateVenda.replaceAll(Pattern.quote("{{month}}"), String.valueOf(cal.get(Calendar.MONTH)+1));

		} catch (Exception e) { }

		Resource sourceData = new ClassPathResource("data.json");
		Resource sourceDataGenerated = new InputStreamResource(new java.io.ByteArrayInputStream(templateVenda.getBytes()));

		Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
		// Set a custom ObjectMapper if Jackson customization is needed
		factory.setResources(new Resource[] { sourceData, sourceDataGenerated });
		//factory.setResources(new Resource[] { sourceData });
		return factory;
	}

}
