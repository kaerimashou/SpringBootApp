package org.azati.first_test_task.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("org.azati.first_test_task")
@EnableJpaRepositories("org.azati.first_test_task.repository")
public class SpringConfig {
}
