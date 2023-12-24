package com.example.springboottutorial.config;

import com.example.springboottutorial.domain.creditcard.CreditCard;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
public class CardDatabaseConfiguration {

    @Bean
    @ConfigurationProperties("spring.two.datasource")
    public DataSourceProperties cardDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource cardDataSource(@Qualifier("cardDataSourceProperties") DataSourceProperties cardDataSourceProperties) {
        return cardDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean cardEntityManager(@Qualifier("cardDataSource") DataSource dataSource,
                                                                    EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource)
                .packages(CreditCard.class)
                .persistenceUnit("card")
                .build();
    }
}
