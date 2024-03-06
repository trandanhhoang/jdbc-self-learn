package com.example.jdbcselflearn.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.example.jdbcselflearn.db.entity.BankUserInfoEntity;
import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {"com.example.jdbcselflearn.db"},
    entityManagerFactoryRef = "bankBindingManagerFactory",
    transactionManagerRef = "bankBindingTransactionManager"
)
public class BankBindingDbConfig {

//  @Bean
//  @ConfigurationProperties("database.bank-binding")
//  @Qualifier("bankBindingHikariDatasource")
//  public HikariDataSource bankBindingHikariDatasource() {
//    return DataSourceBuilder.create().type(HikariDataSource.class).build();
//  }

  // what happen if we remove @Primary?
  @Bean
  public DataSource bankBindingDatasource() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
    hikariConfig.setJdbcUrl("jdbc:mysql://10.50.1.25:3307/ud_bank_function?useSSL=false&useUnicode=yes&characterEncoding=UTF-8");
    hikariConfig.setUsername("dbgtest");
    hikariConfig.setPassword("abc@123");
    ;
    SLF4JQueryLoggingListener loggingListener = new SLF4JQueryLoggingListener();

    return ProxyDataSourceBuilder
        .create(new HikariDataSource(hikariConfig))
        .name("bankBindingDatasource")
        .listener(loggingListener)
        .countQuery()
        .logQueryToSysOut()
        .build();
  }
//  @Bean("bankBindingJdbcTemplate")
//  public NamedParameterJdbcTemplate bankBindingJdbcTemplate(@Qualifier("bankBindingDatasource") DataSource dataSource) {
//    return new NamedParameterJdbcTemplate(dataSource);
//  }

  @Bean
  public LocalContainerEntityManagerFactoryBean bankBindingManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder,
                                                                          DataSource bankBindingDatasource) {
    return entityManagerFactoryBuilder
        .dataSource(bankBindingDatasource)
        .packages(BankUserInfoEntity.class)
        .persistenceUnit("BankBinding")
        .build();
  }

  @Bean
  public PlatformTransactionManager bankBindingTransactionManager(EntityManagerFactory bankBindingManagerFactory) {
    return new JpaTransactionManager(bankBindingManagerFactory);
  }
}
