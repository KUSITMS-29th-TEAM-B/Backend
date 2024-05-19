package com.bamyanggang.apimodule.domain.jobDescription.application

import com.bamyanggang.apimodule.domain.jobDescription.application.service.ApplyInfoGetService
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyReader
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

class ApplicationInfoGetServiceTest: BehaviorSpec({
    val mockApplyReader = mockk<ApplyReader>(relaxed = true)
    val service =  ApplyInfoGetService(mockApplyReader)

    given("ApplyInfoGetService.getApplyInfo") {
        val jobDescriptionId: UUID = UUID.randomUUID()
        `when`("jobDescriptionId가 주어지면") {
            service.getApplyInfo(jobDescriptionId)
            then("readApplyByJobDescriptionId가 호출된다.") {
                verify {
                    mockApplyReader.readApplyByJobDescriptionId(jobDescriptionId)
                }
            }
        }
    }

})
