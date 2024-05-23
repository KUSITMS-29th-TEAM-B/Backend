package com.bamyanggang.domainmodule.domain.tag

import com.bamyanggang.domainmodule.domain.tag.aggregate.Tag
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.*

@DisplayName("태그 도메인 테스트")
class TagTest: DescribeSpec({
    describe("태그 도메인 관련 유닛 테스트") {
        context("태그의 이름과 인자로 넘어온 이름이 동일할 때") {
            it("true 반환한다.") {
                val duplicatedName = "중복된 이름"

                val originTag = Tag.create(duplicatedName, null, UUID.randomUUID())
                val newTag = Tag.create(duplicatedName, null, UUID.randomUUID())

                originTag.isDuplicatedName(newTag.name) shouldBe true
            }
        }

        context("태그의 이름이 인자로 넘어온 이름과 동일하지 않을 때") {
            it("false를 반환한다.") {
                val originTag = Tag.create("기존 태그", null, UUID.randomUUID())
                val newTag = Tag.create("새로운 태그", null, UUID.randomUUID())

                originTag.isDuplicatedName(newTag.name) shouldBe false
            }
        }

        context("태그 이름으로 공백이 넘어오면") {
            it("IllegalmentArgumentException을 발생시킨다.") {
                val emptyTitle = ""
                shouldThrow<IllegalArgumentException> {
                    Tag.create(emptyTitle, null, UUID.randomUUID())
                    Tag.create(emptyTitle, UUID.randomUUID(), UUID.randomUUID())
                }
            }
        }
    }
})
