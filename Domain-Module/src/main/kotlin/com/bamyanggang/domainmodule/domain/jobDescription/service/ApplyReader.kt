package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.Apply
import com.bamyanggang.domainmodule.domain.jobDescription.repository.ApplyRepository
import java.util.*

class ApplyReader(
    private val applyRepository: ApplyRepository
) {

    fun readApplyByJobDescriptionId(JobDescriptionId: UUID): Apply{
        return applyRepository.findByJobDescriptionId(JobDescriptionId)
    }

    fun readApplyExists(JobDescriptionId: UUID): Boolean{
        return applyRepository.existsByJobDescriptionId(JobDescriptionId)
    }

}
