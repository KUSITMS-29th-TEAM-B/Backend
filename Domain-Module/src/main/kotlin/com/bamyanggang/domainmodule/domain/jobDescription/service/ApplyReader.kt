package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.Apply
import com.bamyanggang.domainmodule.domain.jobDescription.repository.ApplyRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ApplyReader(
    private val applyRepository: ApplyRepository
) {

    fun readApplyByJobDescriptionId(JobDescriptionId: UUID): Apply{
        return applyRepository.findByJobDescriptionId(JobDescriptionId)
    }

}
