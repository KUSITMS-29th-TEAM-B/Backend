package com.bamyanggang.apimodule.domain.jobDescription.application

import com.bamyanggang.apimodule.domain.jobDescription.application.service.JobDescriptionDeleteService
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionRemover
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class JobDescriptionDeleteServiceTest: BehaviorSpec({
    val jobRemover = mockk<JobDescriptionRemover>(relaxed = true)
    val jobDescriptionDeleteService = JobDescriptionDeleteService(jobRemover)

    given("JobDescriptionDeleteService.deleteJobDescription") {
        val jobDescriptionId = UUID.randomUUID()
        `when`("jobDescriptionId가 주어지면") {
            jobDescriptionDeleteService.deleteJobDescription(jobDescriptionId)
            then("removeJobDescription가 호출된다.") {
                verify { jobRemover.removeJobDescription(jobDescriptionId) }
            }
        }
    }

})
