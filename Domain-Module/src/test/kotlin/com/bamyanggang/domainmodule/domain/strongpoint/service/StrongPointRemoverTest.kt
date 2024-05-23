package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.domainmodule.domain.strongpoint.exception.StrongPointExceptionMessage
import com.bamyanggang.domainmodule.domain.strongpoint.repository.StrongPointRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class StrongPointRemoverTest : BehaviorSpec({
    val strongPointRepository = mockk<StrongPointRepository>(relaxed = true)
    val strongPointRemover = StrongPointRemover(strongPointRepository)

    Given("삭제할 역량 키워드 id가 주어졌을 때") {
        val deleteStrongPointId = UUID.randomUUID()

        When("StrongPointReader.removeStrongPoint 함수가 호출되면") {
            every { strongPointRepository.isExistByStrongPointId(deleteStrongPointId) } returns true
            strongPointRemover.removeStrongPoint(deleteStrongPointId)

            Then("strongPointRepository.deleteByStrongPointId 함수가 호출된다.") {
                verify { strongPointRepository.deleteByStrongPointId(deleteStrongPointId) }
            }
        }
    }

    Given("존재하지 않는 역량 키워드 id가 주어졌을 때") {
        val deleteStrongPointId = UUID.randomUUID()

        When("StrongPointReader.removeStrongPoint 함수가 호출되면") {
            every { strongPointRepository.isExistByStrongPointId(deleteStrongPointId) } returns false
            val exception = shouldThrow<IllegalArgumentException> {
                strongPointRemover.removeStrongPoint(deleteStrongPointId)
            }

            Then("IllegalAgumentException이 발생한다.") {
                assert(exception.message == StrongPointExceptionMessage.NOT_FOUND_STRONG_POINT.message)
            }
        }
    }
})
