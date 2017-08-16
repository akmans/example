package example.springdata.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import example.springdata.jpa.audit.AuditingDateTimeProvider;
import example.springdata.jpa.audit.CurrentTimeDateTimeService;
import example.springdata.jpa.audit.DateTimeService;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@EnableJpaRepositories(basePackages = {
		"example.springdata.jpa.repositories" }, namedQueriesLocation = "classpath:/jpa/*-jpa-named-queries.properties")
@EnableTransactionManagement
public class RepositoryConfig {

	@Bean
	DateTimeService currentTimeDateTimeService() {
		return new CurrentTimeDateTimeService();
	}

	@Bean
	DateTimeProvider dateTimeProvider(DateTimeService dateTimeService) {
		return new AuditingDateTimeProvider(dateTimeService);
	}
}