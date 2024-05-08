package com.bamyanggang.supportmodule.jwt.config

import com.bamyanggang.supportmodule.jwt.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class ConfigurationPropertiesConfig
