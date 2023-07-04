package com.unity.multi.sources.config;

import com.unity.multi.sources.stores.model.Customer;
import com.unity.multi.sources.stores.model.Order;
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
@EnableJpaRepositories(basePackages = "com.unity.multi.sources.stores.repository",
        entityManagerFactoryRef = "storeEntityManagerFactory",
        transactionManagerRef = "storeTransactionManager")
public class StoresDataSourceConfig {


    @Bean
    @ConfigurationProperties("spring.datasource.store")
    public DataSourceProperties storeDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    @ConfigurationProperties("spring.datasource.store.configuration")
    public DataSource storeDataSource() {
        return storeDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "storeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean storeEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(storeDataSource())
                .packages(Customer.class, Order.class)
                .build();
    }


    @Bean(name = "storeTransactionManager")
    public PlatformTransactionManager storeTransactionManager(
            final @Qualifier("storeEntityManagerFactory") LocalContainerEntityManagerFactoryBean storeEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(storeEntityManagerFactory.getObject()));
    }


}
