package com.bamyanggang.domainmodule.domain.tag.service

import com.bamyanggang.domainmodule.domain.tag.repository.TagRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class TagAppenderTest : BehaviorSpec({
    val mockTagRepository = mockk<TagRepository>(relaxed = true)
    val tagAppender = TagAppender(mockTagRepository)

    Given("상위 태그 이름과 userId가 주어졌을 때") {
        val name = "상위 태그 이름"
        val userId = UUID.randomUUID()

        When("appendParentTag를 호출하면") {
            val newParentTag = tagAppender.appendParentTag(name, userId)

            Then("tagRepository.save를 호출한다.") {
                verify { mockTagRepository.save(newParentTag) }
            }
        }
    }

    Given("하위 태그 이름과 userId, parentTagId가 주어졌을 때") {
        val name = "하위 태그 이름"
        val userId = UUID.randomUUID()
        val parentTagId = UUID.randomUUID()

        When("appendChildTag를 호출하면") {
            val newChildTag = tagAppender.appendChildTag(
                name = name,
                userId = userId,
                parentTagId = parentTagId
            )

            Then("tagRepository.save를 호출한다.") {
                verify { mockTagRepository.save(newChildTag) }
            }
        }
    }
})
