package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.domainmodule.domain.strongpoint.repository.StrongPointRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class StrongPointAppenderTest : BehaviorSpec({
    val strongPointRepository = mockk<StrongPointRepository>(relaxed = true)
    val strongPointAppender = StrongPointAppender(strongPointRepository)

    Given("역량 키워드 입력 값이 주어졌을 때") {
        val name = "역량 키워드 이름"
        val userId: UUID = UUID.randomUUID()

        When("StrongPointAppender.appendStrongPoint 함수가 호출되면") {
            strongPointAppender.appendStrongPoint(name, userId)

            Then("StrongPoint.create와 strongPointRepository.save가 호출된다.") {
                verify {
                    strongPointRepository.save(match { strongPoint ->
                        strongPoint.name == name && strongPoint.userId == userId
                    })
                }
            }
        }
    }

    Given("역량 키워드 이름 배열과 userId가 주어졌을 때") {
        val names = arrayListOf(
            "역량 키워드 이름 1",
            "역량 키워드 이름 2",
            "역량 키워드 이름 3",
        )

        val userId: UUID = UUID.randomUUID()

        When("StrongPointAppender.appendAllStrongPoint 함수가 호출되면") {
            strongPointAppender.appendAllStrongPoint(names, userId)

            Then("StrongPointRepository.saveAll이 호출된다.") {
                verify { strongPointRepository.saveAll(any()) }
            }
        }
    }
})
