package com.unity.multi.sources.config;

import com.unity.multi.sources.shelter.entity.Animal;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.unity.multi.sources.shelter.repository",
        entityManagerFactoryRef = "mariadbEntityManagerFactory",
        transactionManagerRef = "mariadbTransactionManager")
public class MariaDbDataSourceConfig {


    @Bean
    @ConfigurationProperties("spring.datasource.mariadb")
    public DataSourceProperties mariadbDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    @ConfigurationProperties("spring.datasource.mariadb.configuration")
    public DataSource mariadbDataSource() {
        return mariadbDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "mariadbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mariadbEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(mariadbDataSource())
                .packages(Animal.class)
                .build();
    }


    @Bean(name = "mariadbTransactionManager")
    public PlatformTransactionManager mariadbTransactionManager(
            final @Qualifier("mariadbEntityManagerFactory") LocalContainerEntityManagerFactoryBean mariadbEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(mariadbEntityManagerFactory.getObject()));
    }


}
