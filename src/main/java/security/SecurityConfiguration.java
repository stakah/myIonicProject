package security;

import org.springframework.orm.jpa.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.transaction.*;
import org.springframework.transaction.annotation.*;


/**
 * Classe que configura os beans para persistencia
 * 
 * @author Usuário de Teste
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "security-EntityManagerFactory",
        transactionManagerRef = "security-TransactionManager"
)
class SecurityConfiguration {
  
    @Primary

    @Bean(name="security-EntityManagerFactory")
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("security");
        return factoryBean;
    }

    @Bean(name = "security-TransactionManager")
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }


}


