package com.bamyanggang.domainmodule.domain.auth.jwt.config

import com.bamyanggang.domainmodule.domain.auth.jwt.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class ConfigurationPropertiesConfig
