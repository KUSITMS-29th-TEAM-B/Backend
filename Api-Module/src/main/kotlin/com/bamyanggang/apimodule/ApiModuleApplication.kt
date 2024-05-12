package com.bamyanggang.apimodule

import com.bamyanggang.apimodule.global.config.ComponentScanConfig
import com.bamyanggang.domainmodule.common.config.DomainConfig
import com.bamyanggang.infrastructuremodule.persistence.config.PersistenceConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication(
    exclude = [UserDetailsServiceAutoConfiguration::class]
)
@Import(
    value = [
        ComponentScanConfig::class,
        DomainConfig::class,
        PersistenceConfig::class
    ]
)
class ApiModuleApplication {
}

fun main(args: Array<String>) {
    runApplication<ApiModuleApplication>(*args)
}
