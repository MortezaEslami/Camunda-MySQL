package com.example.workflow.configuaration;

import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@AllArgsConstructor
@Configuration
public class ExampleProcessEngineConfiguration {

    private ResourcePatternResolver resourceLoader;

    @Bean
    @ConfigurationProperties(prefix = "datasource.original")
    @Primary
    public DataSourceProperties originalDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("db1")
    @Primary
    public DataSource originalDataSource() {
        DataSourceProperties originalDataSourceProperties = originalDataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(originalDataSourceProperties.getDriverClassName())
                .url(originalDataSourceProperties.getUrl())
                .username(originalDataSourceProperties.getUsername())
                .password(originalDataSourceProperties.getPassword())
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.camunda")
    public DataSourceProperties camundaDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("db2")
    public DataSource camnundaDataSource() {
        DataSourceProperties camundaDataSourceProperties = camundaDataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(camundaDataSourceProperties.getDriverClassName())
                .url(camundaDataSourceProperties.getUrl())
                .username(camundaDataSourceProperties.getUsername())
                .password(camundaDataSourceProperties.getPassword())
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(camnundaDataSource());
    }

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration() throws IOException {
        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();

        config.setDataSource(camnundaDataSource());
        config.setTransactionManager(transactionManager());

        config.setDatabaseSchemaUpdate("true");
        config.setHistory("audit");
        config.setJobExecutorActivate(true);

        Resource[] resources = resourceLoader.getResources("classpath:/*.bpmn");
        config.setDeploymentResources(resources);

        return config;
    }
}