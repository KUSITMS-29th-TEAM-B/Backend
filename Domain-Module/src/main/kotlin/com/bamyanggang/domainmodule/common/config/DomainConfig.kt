package com.bamyanggang.domainmodule.common.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.bamyanggang.domainmodule"])
@ConfigurationPropertiesScan(basePackages = ["com.bamyanggang.domainmodule"])
class DomainConfig
