package com.bamyanggang.apimodule.domain.jobDescription.application

import com.bamyanggang.apimodule.domain.jobDescription.application.service.JobDescriptionInfoGetService
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionReader
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class JobDescriptionInfoGetServiceTest : BehaviorSpec({
    val jobDescriptionReader = mockk<JobDescriptionReader>()
    val jobDescriptionInfoGetService = JobDescriptionInfoGetService(jobDescriptionReader)

    Given("jobDescriptionId가 들어온 경우") {
        val jobDescriptionId = UUID.randomUUID()
        val jobDescription: JobDescription = generateFixture()
        every { jobDescriptionReader.readJobDescriptionById(jobDescriptionId) } returns jobDescription

        When("getJobDescriptionDetail가 호출된다") {
            jobDescriptionInfoGetService.getJobDescriptionDetail(jobDescriptionId)

            Then("jobDescriptionReader.readJobDescriptionById가 호출된다") {
                verify { jobDescriptionReader.readJobDescriptionById(jobDescriptionId) }
            }
        }
    }

})
