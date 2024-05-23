package com.bamyanggang.domainmodule.domain.tag.service

import com.bamyanggang.domainmodule.domain.tag.exception.TagException
import com.bamyanggang.domainmodule.domain.tag.repository.TagRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class TagRemoverTest : BehaviorSpec({
    val mockTagRepository = mockk<TagRepository>(relaxed = true)
    val tagRemover = TagRemover(mockTagRepository)

    Given("삭제할 태그 Id가 주어졌을 때") {
        val deleteTagId = UUID.randomUUID()

        When("removeTag가 호출되면") {
            every { mockTagRepository.isExistById(deleteTagId) } returns true
            tagRemover.removeTag(deleteTagId)

            Then("tagRepository.deleteById가 호출된다.") {
                verify { mockTagRepository.deleteByTagId(deleteTagId) }
            }
        }
    }

    Given("존재하지 않는 태그 Id가 주어졌을 때") {
        val notExistTagId = UUID.randomUUID()

        When("removeTag가 호출되면") {
            val exception = shouldThrow<TagException.NotFoundTag> {
                tagRemover.removeTag(notExistTagId)
            }

            Then("TagException.NotFoundTag 예외가 발생한다.") {
                assert(exception.message == TagException.NotFoundTag().message)
            }
        }
    }
})
