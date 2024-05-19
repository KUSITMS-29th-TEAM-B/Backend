package com.bamyanggang.apimodule.domain.experience.application

import com.bamyanggang.apimodule.domain.experience.application.service.ExperienceDeleteService
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceRemover
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class ExperienceDeleteServiceTest : BehaviorSpec({
    val experienceRemover = mockk<ExperienceRemover>()
    val experienceDeleteService = ExperienceDeleteService(experienceRemover)

    Given("ExperienceDeleteService 테스트") {
        When("삭제할 UUID가 주어지면") {
            val deleteId = UUID.randomUUID()
            every { experienceDeleteService.deleteExperienceById(deleteId) } returns Unit

            experienceDeleteService.deleteExperienceById(deleteId)

            Then("ExperienceRemover.remove가 호출된다.") {
                verify { experienceRemover.remove(deleteId) }
            }
        }
    }
})
