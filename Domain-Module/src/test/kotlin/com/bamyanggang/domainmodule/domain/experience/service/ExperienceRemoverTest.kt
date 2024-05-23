package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class ExperienceRemoverTest : BehaviorSpec({
    val experienceRepository = mockk<ExperienceRepository>(relaxed = true)
    val experienceRemover = ExperienceRemover(experienceRepository)

    Given("삭제할 경험 id가 주어졌을 때") {
        val deleteExperienceId = UUID.randomUUID()

        When("ExperienceRemover.remove가 호출되면") {
            experienceRemover.remove(deleteExperienceId)

            Then("experienceRepository.deleteByExperienceId가 호출된다.") {
                verify { experienceRepository.deleteByExperienceId(deleteExperienceId) }
            }
        }
    }
})
