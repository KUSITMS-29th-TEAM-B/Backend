package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.domainmodule.domain.strongpoint.repository.KeywordRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class KeywordReaderTest : BehaviorSpec({
    val keywordRepository = mockk<KeywordRepository>(relaxed = true)
    val keywordReader = KeywordReader(keywordRepository)

    Given("기본 역량 키워드 id 배열이 주어졌을 때") {
        val defaultStrongPoints = arrayListOf(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
        )

        When("KeywordReader.readByIds 함수가 호출되면") {
            keywordReader.readByIds(defaultStrongPoints)

            Then("keywordRepository.findByIds 함수가 호출된다.") {
                verify { keywordRepository.findByIds(defaultStrongPoints) }
            }
        }
    }
})
