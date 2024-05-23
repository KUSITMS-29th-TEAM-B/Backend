package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceStrongPoint
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDateTime
import java.util.*

class ExperienceModifierTest : BehaviorSpec({
    val experienceRepository = mockk<ExperienceRepository>(relaxed = true)
    val experienceModifier = ExperienceModifier(experienceRepository)

    Given("수정할 경험 등록 데이터들이 주어졌을 때") {
        val experienceId = UUID.randomUUID()
        val title = "수정한 경험 제목"
        val parentTagId: UUID = UUID.randomUUID()
        val childTagId: UUID = UUID.randomUUID()
        val contents = arrayListOf(
            ExperienceContent(
                question = "수정한 질문 1",
                answer = "수정한 대답 1"
            ),
            ExperienceContent(
                question = "수정한 질문 2",
                answer = "수정한 대답 2"
            )
        )

        val experienceStrongPoints: List<ExperienceStrongPoint> = arrayListOf(
            ExperienceStrongPoint.create(
                UUID.randomUUID()
            ),
            ExperienceStrongPoint.create(
                UUID.randomUUID()
            )
        )
        val startedAt: LocalDateTime = LocalDateTime.now()
        val endedAt: LocalDateTime = startedAt.plusDays(1)
        val userId = UUID.randomUUID()

        When("ExperienceModifier.modifyExperience 호출하면") {
            val editExperience = experienceModifier.modifyExperience(
                experienceId = experienceId,
                title = title,
                parentTagId = parentTagId,
                childTagId = childTagId,
                contents = contents,
                experienceStrongPoints = experienceStrongPoints,
                userId = userId,
                startedAt = startedAt,
                endedAt = endedAt
            )

            Then("experienceRepository.save가 호출된다.") {
                verify { experienceRepository.save(editExperience) }
            }
        }
    }

})
