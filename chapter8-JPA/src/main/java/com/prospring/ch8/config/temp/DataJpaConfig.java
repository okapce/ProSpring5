package com.prospring.ch8.config.temp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.prospring.ch8"})
@EnableJpaRepositories(basePackages = {"com.prospring.ch8"})
public class DataJpaConfig {
 private static Logger logger = LoggerFactory.getLogger(DataJpaConfig.class);
 @Bean
 public DataSource dataSourceDJPA() {
	 try {
		 EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
		 return dbBuilder.setType(EmbeddedDatabaseType.H2) .addScripts("classpath:sql/schema.sql", "classpath:sql/test-data.sql").build();
	 } catch (Exception e) {
		 logger.error("Embedded DataSource bean cannot be created!", e);
		 return null;
	 }
 }
 
 @Bean
 public PlatformTransactionManager transactionManagerDJPA() {
	 return new JpaTransactionManager(entityManagerFactoryDJPA());
 }
 @Bean
 public JpaVendorAdapter DJPAVendorAdapter() {
	 return new HibernateJpaVendorAdapter();
 }
 @Bean
 public Properties hibernatePropertiesDJPA() {
	 Properties hibernateProp = new Properties();
	 hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	 hibernateProp.put("hibernate.format_sql", true);
	 hibernateProp.put("hibernate.use_sql_comments", true);
	 hibernateProp.put("hibernate.show_sql", true);
	 hibernateProp.put("hibernate.max_fetch_depth", 3);
	 hibernateProp.put("hibernate.jdbc.batch_size", 10);
	 hibernateProp.put("hibernate.jdbc.fetch_size", 50);
	 return hibernateProp;
 }
 
 @Bean
 public EntityManagerFactory entityManagerFactoryDJPA() {
	 LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
	 factoryBean.setPackagesToScan("com.prospring.ch8.entities");
	 factoryBean.setDataSource(dataSourceDJPA());
	 factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	 factoryBean.setJpaProperties(hibernatePropertiesDJPA());
	 factoryBean.setJpaVendorAdapter(DJPAVendorAdapter());
	 factoryBean.afterPropertiesSet();
	 return factoryBean.getNativeEntityManagerFactory();
 }
}