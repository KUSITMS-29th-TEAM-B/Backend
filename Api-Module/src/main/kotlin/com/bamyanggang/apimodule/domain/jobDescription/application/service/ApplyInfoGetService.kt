package com.bamyanggang.apimodule.domain.jobDescription.application.service

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.ApplyInfo
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ApplyInfoGetService(
    private val applyReader: ApplyReader
) {

    @Transactional(readOnly = true)
    fun getApplyInfo(jobDescriptionId: UUID): ApplyInfo.Response {
        return applyReader.readApplyByJobDescriptionId(jobDescriptionId).contents.map {
            ApplyInfo.ContentInfo(
                it.question,
                it.answer
            )
        }.let {
            ApplyInfo.Response(it)
        }
    }

}
