package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.strongpoint.repository.StrongPointRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class StrongPointReaderTest : BehaviorSpec({

    val strongPointRepository: StrongPointRepository = mockk<StrongPointRepository>(relaxed = true)
    val strongPointReader = StrongPointReader(strongPointRepository)

    Given("userId가 주어졌을 때") {
        val userId : UUID = generateFixture()

        When("StrongPointReader.readAllByUserId 함수가 호출되면") {
            strongPointReader.readAllByUserId(userId)

            Then("strongPointRepository.findAllByUserId 함수가 호출된다.") {
                verify { strongPointRepository.findAllByUserId(userId) }
            }
        }
    }
})
