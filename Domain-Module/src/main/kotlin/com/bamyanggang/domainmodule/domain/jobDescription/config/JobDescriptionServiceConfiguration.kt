package com.bamyanggang.domainmodule.domain.jobDescription.config

import com.bamyanggang.domainmodule.domain.jobDescription.repository.ApplyRepository
import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository
import com.bamyanggang.domainmodule.domain.jobDescription.service.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JobDescriptionServiceConfiguration(
    private val jobDescriptionRepository: JobDescriptionRepository,
    private val applyRepository: ApplyRepository
) {

    @Bean
    fun jobDescriptionAppender(): JobDescriptionAppender {
        return JobDescriptionAppender(jobDescriptionRepository)
    }

    @Bean
    fun jobDescriptionModifier(): JobDescriptionModifier {
        return JobDescriptionModifier(jobDescriptionRepository)
    }

    @Bean
    fun jobDescriptionReader(): JobDescriptionReader {
        return JobDescriptionReader(jobDescriptionRepository)
    }

    @Bean
    fun jobDescriptionRemover(): JobDescriptionRemover {
        return JobDescriptionRemover(jobDescriptionRepository)
    }

    @Bean
    fun applyAppender(): ApplyAppender {
        return ApplyAppender(applyRepository)
    }

    @Bean
    fun applyModifier(): ApplyModifier {
        return ApplyModifier(applyRepository)
    }

    @Bean
    fun applyReader(): ApplyReader {
        return ApplyReader(applyRepository)
    }


}
