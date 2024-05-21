package com.bamyanggang.apimodule.domain.strongpoint.config

import com.bamyanggang.domainmodule.domain.strongpoint.repository.StrongPointRepository
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointAppender
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointReader
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointRemover
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StrongPointServiceConfiguration(
    private val strongPointRepository: StrongPointRepository
) {

    @Bean
    fun strongPointAppender(): StrongPointAppender {
        return StrongPointAppender(strongPointRepository)
    }

    @Bean
    fun strongPointReader(): StrongPointReader {
        return StrongPointReader(strongPointRepository)
    }

    @Bean
    fun strongPointModifier(): StrongPointRemover{
        return StrongPointRemover(strongPointRepository)
    }

}
