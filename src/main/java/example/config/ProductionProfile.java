package example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@Profile("default")
@PropertySources({ @PropertySource("classpath:/config/system.properties") })
public class ProductionProfile {
}