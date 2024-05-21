package com.bamyanggang.domainmodule.domain.experience.config

import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceAppender
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceModifier
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceReader
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceRemover
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ExperienceServiceConfiguration(
    private val experienceRepository: ExperienceRepository
) {

    @Bean
    fun experienceAppender(): ExperienceAppender {
        return ExperienceAppender(experienceRepository)
    }

    @Bean
    fun experienceModifier(): ExperienceModifier {
        return ExperienceModifier(experienceRepository)
    }

    @Bean
    fun experienceReader(): ExperienceReader {
        return ExperienceReader(experienceRepository)
    }

    @Bean
    fun experienceRemover(): ExperienceRemover {
        return ExperienceRemover(experienceRepository)
    }

}
