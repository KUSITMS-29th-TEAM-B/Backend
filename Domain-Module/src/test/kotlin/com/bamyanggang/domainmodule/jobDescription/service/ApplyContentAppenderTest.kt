package com.bamyanggang.domainmodule.jobDescription.service

import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.repository.ApplyContentRepository
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyContentAppender
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

class ApplyContentAppenderTest : BehaviorSpec({
    val applyContentRepository = mockk<ApplyContentRepository>(relaxed = true)
    val applyContentAppender = ApplyContentAppender(applyContentRepository)

    given("ApplyContentAppender.appendApplyContent") {
        val applyId: UUID = generateFixture()
        val question: String = generateFixture()
        val answer: String = generateFixture()

        `when`("appendApplyContent이 호출되면") {
            applyContentAppender.appendApplyContent(
                applyId = applyId,
                question = question,
                answer = answer
            )

            then("ApplyContent.create와 applyContentRepository.save가 호출된다.") {
                verify {
                    applyContentRepository.save(match { applyContent ->
                        applyContent.question == question &&
                            applyContent.answer == answer &&
                            applyContent.applyId == applyId
                    })
                }
            }
        }
    }
})
