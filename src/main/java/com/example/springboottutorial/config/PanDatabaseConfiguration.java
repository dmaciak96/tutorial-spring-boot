package com.example.springboottutorial.config;

import com.example.springboottutorial.domain.pan.CreditCardPAN;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
public class PanDatabaseConfiguration {

    @Bean
    @ConfigurationProperties("spring.three.datasource")
    public DataSourceProperties panDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource panDataSource(@Qualifier("panDataSourceProperties") DataSourceProperties panDataSourceProperties) {
        return panDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean panEntityManager(@Qualifier("panDataSource") DataSource dataSource,
                                                                   EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource)
                .packages(CreditCardPAN.class)
                .persistenceUnit("pan")
                .build();
    }

    @Bean
    public PlatformTransactionManager panTransactionManager(
            @Qualifier("panEntityManager") LocalContainerEntityManagerFactoryBean entityManager) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManager.getObject()));
    }
}
