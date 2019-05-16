package com.example.customertracker.config;

import static org.hibernate.cfg.Environment.*;

import java.beans.PropertyVetoException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.example.customertracker.dao"),
		@ComponentScan("com.example.customertracker.service") })
public class AppConfig {
	@Autowired
	private Environment env;
	
	public ComboPooledDataSource getDataSource() throws PropertyVetoException {
		ComboPooledDataSource datasource = new ComboPooledDataSource();
		datasource.setDriverClass(env.getProperty("jdbc.driverClassName"));
		datasource.setJdbcUrl(env.getProperty("jdbc.db_url"));
		datasource.setUser(env.getProperty("jdbc.username"));
		datasource.setPassword(env.getProperty("jdbc.password"));
		return datasource;
	}

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		Properties props = new Properties();
		// Setting JDBC properties
		props.put(DRIVER, env.getProperty("jdbc.driverClassName"));
		props.put(URL, env.getProperty("jdbc.db_url"));
		props.put(USER, env.getProperty("jdbc.username"));
		props.put(PASS, env.getProperty("jdbc.password"));
		
		// Setting Hibernate properties
		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		props.put(DIALECT, env.getProperty("hibernate.dialect"));

		// Setting C3P0 properties
		props.put(C3P0_MIN_SIZE, env.getProperty("c3p0.minPoolSize"));
		props.put(C3P0_MAX_SIZE, env.getProperty("c3p0.maxPoolSize"));
		props.put(C3P0_TIMEOUT, env.getProperty("c3p0.maxIdleTime"));
		

		factoryBean.setHibernateProperties(props);
		factoryBean.setPackagesToScan("com.example.customertracker.model");

		return factoryBean;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
}
