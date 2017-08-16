package example.config;

import java.beans.PropertyVetoException;
import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment env;

	@Bean(name = "validationMessageSource")
	public ReloadableResourceBundleMessageSource validationMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/i18n/ValidationMessages");
		messageSource.setCacheSeconds(10); // reload messages every 10 seconds
		return messageSource;
	}

	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource((MessageSource) validationMessageSource());
		return validator;
	}

	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSourceConfig = new ComboPooledDataSource();
		dataSourceConfig.setDriverClass(env.getRequiredProperty("db.driver"));
		dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
		dataSourceConfig.setUser(env.getRequiredProperty("db.username"));
		dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));

		// the settings below are optional -- c3p0 can work with defaults
		dataSourceConfig.setMinPoolSize(3);
		dataSourceConfig.setAcquireIncrement(5);
		dataSourceConfig.setMaxPoolSize(20);
		dataSourceConfig.setMaxStatements(180);

		return dataSourceConfig;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {

		HibernateJpaVendorAdapter hibernateJpa = new HibernateJpaVendorAdapter();
		hibernateJpa.setDatabasePlatform(env.getProperty("hibernate.dialect"));
		hibernateJpa.setShowSql(env.getProperty("hibernate.show_sql", Boolean.class));

		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("example.springdata.jpa.entities");
		emf.setJpaVendorAdapter(hibernateJpa);
		emf.setJpaPropertyMap(Collections.singletonMap("javax.persistence.validation.mode", "none"));
		return emf;
	}

	@Bean
	public JpaTransactionManager transactionManager() throws PropertyVetoException {
		JpaTransactionManager txnMgr = new JpaTransactionManager();
		txnMgr.setEntityManagerFactory(entityManagerFactory().getObject());
		return txnMgr;
	}
}