package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class JobDescriptionRemoverTest: BehaviorSpec({
    val jobDescriptionRepository = mockk<JobDescriptionRepository>(relaxed = true)
    val service = JobDescriptionRemover(jobDescriptionRepository)

    given("JobDescriptionRemover.removeJobDescription") {
        val jobDescriptionId: UUID = UUID.randomUUID()
        `when`("jobDescriptionId가 주어지면") {
            service.removeJobDescription(jobDescriptionId)
            then("removeJobDescription가 호출된다.") {
                verify {
                    jobDescriptionRepository.deleteById(jobDescriptionId)
                }
            }
        }
    }

})
