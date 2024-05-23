package com.bamyanggang.domainmodule.domain.tag.service

import com.bamyanggang.domainmodule.domain.tag.repository.TagRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class TagReaderTest : BehaviorSpec({
    val mockTagRepository = mockk<TagRepository>(relaxed = true)
    val tagReader = TagReader(mockTagRepository)

    Given("tagId가 주어졌을 때") {
        val tagId = UUID.randomUUID()

        When("tagReader.readById가 호출되면") {
            tagReader.readById(tagId)

            Then("tagRepository.findById가 호촐된다.") {
                verify { mockTagRepository.findById(tagId) }
            }
        }
    }

    Given("tagId 배열이 주어졌을 때") {
        val tagIds = arrayListOf(
            UUID.randomUUID(),
            UUID.randomUUID()
        )

        When("tagReader.readByParentTagIds가 호출되면") {
            tagReader.readByParentTagIds(tagIds)

            Then("tagRepository.findByParentTagIds 호촐된다.") {
                verify { mockTagRepository.findByParentTagIds(tagIds) }
            }
        }
    }

    Given("userId가 주어졌을 때") {
        val userId = UUID.randomUUID()

        When("tagReader.readAllParentTagsByUserId 호출되면") {
            tagReader.readAllParentTagsByUserId(userId)

            Then("tagRepository.findAllParentTagsByUserId 호촐된다.") {
                verify { mockTagRepository.findAllParentTagsByUserId(userId) }
            }
        }
    }

    Given("userId, parentTagId가 주어졌을 때") {
        val userId = UUID.randomUUID()
        val parentTagId = UUID.randomUUID()
        When("tagReader.readAllChildTagsByUserIdAndParentTagId 호출되면") {
            tagReader.readAllChildTagsByUserIdAndParentTagId(
                userId = userId,
                parentTagId = parentTagId
            )

            Then("tagRepository.findAllChildTagsByUserIdAndParentTagId 호촐된다.") {
                verify { mockTagRepository.findAllChildTagsByUserIdAndParentTagId(
                    userId = userId,
                    parentTagId = parentTagId
                ) }
            }
        }
    }

    Given("userId, search 검색 문자열이 주어졌을 때") {
        val userId = UUID.randomUUID()
        val search = "태그 이름 검색 문자열"
        When("tagReader.readIdsByUserIdAndNameContains 호출되면") {
            tagReader.readIdsByUserIdAndNameContains(
                userId = userId,
                search = search
            )

            Then("tagRepository.findAllChildTagsByUserIdAndParentTagId 호촐된다.") {
                verify { mockTagRepository.findByUserIdAndNameContains(
                    userId = userId,
                    search = search
                ) }
            }
        }
    }

    Given("parentTagId가 주어졌을 때") {
        val parentTagId = UUID.randomUUID()

        When("tagReader.readChildTagsByParentTagId 호출되면") {
            tagReader.readChildTagsByParentTagId(parentTagId = parentTagId)

            Then("tagRepository.findAllChildTagsByParentTagId 호촐된다.") {
                verify { mockTagRepository.findAllChildTagsByParentTagId(
                    parentTagId = parentTagId,
                ) }
            }
        }
    }
})
