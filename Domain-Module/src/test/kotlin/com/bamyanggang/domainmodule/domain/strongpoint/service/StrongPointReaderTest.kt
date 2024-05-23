package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.strongpoint.repository.StrongPointRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class StrongPointReaderTest : BehaviorSpec({
    val strongPointRepository = mockk<StrongPointRepository>(relaxed = true)
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

    Given("userId 배열이 주어졌을 때") {
        val userIds = arrayListOf(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
        )

        When("StrongPointReader.readByIds 함수가 호출되면") {
            strongPointReader.readByIds(userIds)

            Then("strongPointRepository.findByIds 함수가 호출된다.") {
                verify { strongPointRepository.findByIds(userIds) }
            }
        }
    }

    Given("userId, search 검색 문자열이 주어졌을 때") {
        val userId = UUID.randomUUID()
        val search = "검색 문자열"

        When("StrongPointReader.readIdsByUserIdAndNameContains 함수가 호출되면") {
            strongPointReader.readIdsByUserIdAndNameContains(
                userId = userId,
                search = search)

            Then("strongPointRepository.findByUserIdAndNameContains 함수가 호출된다.") {
                verify { strongPointRepository.findByUserIdAndNameContains(
                    userId = userId,
                    search = search)
                }
            }
        }
    }
})
