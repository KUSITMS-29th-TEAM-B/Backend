package com.bamyanggang.apimodule.domain.jobDescription.application

import com.bamyanggang.apimodule.domain.jobDescription.application.service.JobDescriptionUpdateService
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionModifier
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionReader
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

class JobDescriptionUpdateServiceTest: BehaviorSpec({
    val jobDescriptionModifier = mockk<JobDescriptionModifier>(relaxed = true)
    val jobDescriptionReader = mockk<JobDescriptionReader>(relaxed = true)
    val service = JobDescriptionUpdateService(jobDescriptionModifier, jobDescriptionReader)

    given("JobDescriptionUpdateService.updateWriteStatus") {
        val jobDescriptionId = UUID.randomUUID()
        `when`("직무 공고 id가 주어지면") {
            service.updateWriteStatus(jobDescriptionId)
            then("modifyWriteStatus가 호출된다.") {
                verify { jobDescriptionModifier.modifyWriteStatus(any()) }
            }
        }
    }

})
