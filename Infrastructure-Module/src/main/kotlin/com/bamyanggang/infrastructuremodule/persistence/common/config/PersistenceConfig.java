package com.bamyanggang.infrastructuremodule.persistence.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.bamyanggang.infrastructuremodule.persistence"})
@EnableJpaRepositories(basePackages = {"com.bamyanggang.infrastructuremodule.persistence"})
public class PersistenceConfig {
}
