package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.ApplyContent
import com.bamyanggang.domainmodule.domain.jobDescription.repository.ApplyRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

class ApplyModifierTest: BehaviorSpec({
    val applyRepository = mockk<ApplyRepository>(relaxed = true)
    val applyModifier = ApplyModifier(applyRepository)

    given("ApplyModifier.modifyApplyInfo") {
        `when`("jobDescriptionId와 applyContentList가 주어지면") {
            val jobDescriptionId = UUID.randomUUID()
            val applyContent: ApplyContent = generateFixture{
                it.set("question", "질문")
                it.set("answer", "답변")
            }
            val applyContentList = listOf(applyContent)
            applyModifier.modifyApplyInfo(jobDescriptionId, applyContentList)
            then("applyRepository.findByJobDescriptionId가 호출된다.") {
                verify {
                    applyRepository.findByJobDescriptionId(jobDescriptionId)
                }
            }
            then("applyRepository.save가 호출된다.") {
                verify {
                    applyRepository.save(any())
                }
            }
        }
    }

})
