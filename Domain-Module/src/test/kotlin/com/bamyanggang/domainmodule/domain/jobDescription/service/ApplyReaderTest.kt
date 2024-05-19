package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.domain.jobDescription.repository.ApplyRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

class ApplyReaderTest: BehaviorSpec({
    val applyRepository = mockk<ApplyRepository>(relaxed = true)
    val applyReader = ApplyReader(applyRepository)

    given("ApplyReader.readApplyByJobDescriptionId") {
        val jobDescriptionId: UUID = UUID.randomUUID()
        `when`("jobDescriptionId가 주어지면") {
            applyReader.readApplyByJobDescriptionId(jobDescriptionId)
            then("applyRepository.findByJobDescriptionId가 호출된다.") {
                verify {
                    applyRepository.findByJobDescriptionId(jobDescriptionId)
                }
            }
        }
    }

})
