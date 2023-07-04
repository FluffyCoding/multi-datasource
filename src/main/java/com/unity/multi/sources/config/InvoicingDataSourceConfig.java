package com.unity.multi.sources.config;

import com.unity.multi.sources.invoicing.model.Client;
import com.unity.multi.sources.invoicing.model.Invoice;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.unity.multi.sources.invoicing.repository",
        entityManagerFactoryRef = "invoiceEntityManagerFactory",
        transactionManagerRef = "invoiceTransactionManager")
public class InvoicingDataSourceConfig {


    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.invoicing")
    public DataSourceProperties collegeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.invoicing.configuration")
    public DataSource collegeDataSource() {
        return collegeDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "invoiceEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean invoiceEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(collegeDataSource())
                .packages(Client.class, Invoice.class)
                .build();
    }

    @Primary
    @Bean(name = "invoiceTransactionManager")
    public PlatformTransactionManager collegeTransactionManager(
            final @Qualifier("invoiceEntityManagerFactory") LocalContainerEntityManagerFactoryBean invoiceEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(invoiceEntityManagerFactory.getObject()));
    }


}
