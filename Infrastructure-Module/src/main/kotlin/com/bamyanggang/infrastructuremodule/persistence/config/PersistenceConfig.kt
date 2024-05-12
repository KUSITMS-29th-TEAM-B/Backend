package com.bamyanggang.infrastructuremodule.persistence.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ComponentScan(basePackages = ["com.bamyanggang.infrastructuremodule.persistence"])
@EntityScan(basePackages = ["com.bamyanggang.infrastructuremodule.persistence"])
@EnableJpaRepositories(basePackages = ["com.bamyanggang.infrastructuremodule.persistence"])
class PersistenceConfig
