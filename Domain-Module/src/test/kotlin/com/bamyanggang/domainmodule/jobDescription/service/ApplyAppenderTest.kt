package com.bamyanggang.domainmodule.jobDescription.service

import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.repository.ApplyRepository
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyAppender
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class ApplyAppenderTest : BehaviorSpec({
    val mockApplyRepository = mockk<ApplyRepository>(relaxed = true)
    val applyAppender = ApplyAppender(mockApplyRepository)

    given("ApplyAppender.appendApply") {
        val title : String = generateFixture()
        val jobDescriptionId : UUID = generateFixture()

        `when`("appendApply이 호출되면") {
            applyAppender.appendApply(
                title = title,
                jobDescriptionId = jobDescriptionId
            )

            then("Apply.create와 applyRepository.save가 호출된다.") {
                verify {
                    mockApplyRepository.save(match { apply ->
                        apply.title == title &&
                                apply.jobDescriptionId == jobDescriptionId
                    })
                }
            }
        }
    }

})