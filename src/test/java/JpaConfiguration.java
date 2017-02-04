
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by xy on 2017/2/4.
 */
@Configuration
@EnableJpaRepositories(basePackages = "springboot.dao")
public class JpaConfiguration {
    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/test?characterEncoding=utf8");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("123456");
        return driverManagerDataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean=new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("springboot.model");
        entityManagerFactoryBean.setJpaProperties(buildProperties());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter(){{
            setDatabase(Database.MYSQL);
        }});
        return entityManagerFactoryBean;
    }
    protected Properties buildProperties(){
        Properties hibernateProperties=new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.use_sql_comments", "false");
        hibernateProperties.setProperty("hibernate.format_sql", "true");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.generate_statistics", "false");
        hibernateProperties.setProperty("javax.persistence.validation.mode", "none");
        //Audit History flags
        hibernateProperties.setProperty("org.hibernate.envers.store_data_at_delete", "true");
        hibernateProperties.setProperty("org.hibernate.envers.global_with_modified_flag", "true");
        return hibernateProperties;
    }
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(transactionManager());
    }
}
