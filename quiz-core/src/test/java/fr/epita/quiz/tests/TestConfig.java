package fr.epita.quiz.tests;

import fr.epita.quiz.services.JDBCQuestionRepository;
import fr.epita.quiz.services.QuestionRepository;
import fr.epita.quiz.services.conf.DBProperties;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.hibernate.HibernateTransactionManager;
import org.springframework.orm.jpa.hibernate.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties(DBProperties.class)
public class TestConfig {


    @Bean("services.data.datasource")
    public DataSource getPrimaryDataSource(DBProperties dbProperties) {
      return new DriverManagerDataSource(dbProperties.getUrl(), dbProperties.getUser(),dbProperties.getPwd());
    }

    @Bean("services.data.altdatasource")
    public DataSource getSecondaryDataSource() {
      return new DriverManagerDataSource("jdbc:h2:mem:test2;DB_CLOSE_DELAY=-1", "user", "password");
    }


    @Bean("jpa.hibernate.properties")
    public Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.format_sql", "true");
        return hibernateProperties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(@Qualifier("services.data.datasource") DataSource dataSource,
                                                      @Qualifier("jpa.hibernate.properties") Properties hibernateProperties) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan("fr.epita.quiz.datamodel");
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }






}
