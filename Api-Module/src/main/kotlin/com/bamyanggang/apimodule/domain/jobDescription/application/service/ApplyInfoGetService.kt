package com.bamyanggang.apimodule.domain.jobDescription.application.service

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.ApplyInfo
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyReader
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ApplyInfoGetService(
    private val applyReader: ApplyReader,
    private val jobDescriptionReader: JobDescriptionReader
) {

    @Transactional(readOnly = true)
    fun getApplyInfo(jobDescriptionId: UUID): ApplyInfo.Response {
        val jobDescription = jobDescriptionReader.readJobDescriptionById(jobDescriptionId)
        val contents = applyReader.readApplyByJobDescriptionId(jobDescriptionId).contents.map {
            ApplyInfo.ContentInfo(it.question, it.answer)
        }
        return ApplyInfo.Response(contents, jobDescription.writeStatus)
    }

}
