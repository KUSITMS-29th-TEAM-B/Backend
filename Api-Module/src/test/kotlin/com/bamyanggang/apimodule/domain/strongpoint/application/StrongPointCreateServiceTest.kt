package com.bamyanggang.apimodule.domain.strongpoint.application

import com.bamyanggang.apimodule.domain.strongpoint.application.dto.CreateStrongPoint
import com.bamyanggang.apimodule.domain.strongpoint.application.service.StrongPointCreateService
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import com.bamyanggang.domainmodule.domain.strongpoint.exception.StrongPointException
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointAppender
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointReader
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.test.context.support.WithMockUser
import java.util.*

@ExtendWith(MockKExtension::class)
@WithMockUser
class StrongPointCreateServiceTest: DescribeSpec({
    describe("역량 키워드 저장 Service 테스트") {
        val strongPointReader: StrongPointReader = mockk(relaxed = true)
        val strongPointAppender: StrongPointAppender = mockk(relaxed = true)
        val strongPointCreateService = StrongPointCreateService(strongPointAppender, strongPointReader)
        val userId = generateFixture<UUID>()

        context("역량 키워드 생성이 정상적으로 이루어지면") {
            it("역량 키워드를 저장하고 생성된 역량 키워드 id를 포함한 Response DTO를 반환한다.") {
                val request : CreateStrongPoint.Request = CreateStrongPoint.Request("새로운 역량 키워드")
                val response = strongPointCreateService.createStrongPoint(request, userId)

                response.id shouldNotBe null
            }
        }
        context("등록하는 역량 키워드 이름이 기존에 저장된 역량 키워드 중 하나의 이름과 중복되면") {
            it("StrongPointException.DuplicatedName 예외가 발생한다.") {
                val strongPoint = StrongPoint.create("역량 키워드 1", userId)
                val duplicatedStrongPoint = StrongPoint.create("중복 역량 키워드", userId)

                val currentStrongPoints = arrayListOf(strongPoint, duplicatedStrongPoint)
                val request : CreateStrongPoint.Request = CreateStrongPoint.Request("중복 역량 키워드")

                every { strongPointReader.readAllByUserId(userId) }.returnsMany(currentStrongPoints)

                shouldThrow<StrongPointException.DuplicatedName> {
                    strongPointCreateService.createStrongPoint(request, userId)
                }
            }
        }

        context("역량 키워드 제한 개수(10개)를 초과하여 등록하려 한다면") {
            it("StrongPointException.OverCountLimit 예외가 발생한다.") {
                val request : CreateStrongPoint.Request = CreateStrongPoint.Request("새로운 역량 키워드")

                every { strongPointReader.readAllByUserId(userId) } answers {
                    Array(StrongPoint.LIMIT) { StrongPoint.create("역량 키워드 $it", userId) }.toList()
                }

                shouldThrow<StrongPointException.OverCountLimit> {
                    strongPointCreateService.createStrongPoint(request, userId)
                }
            }
        }
    }
})
