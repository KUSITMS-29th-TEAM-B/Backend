package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDate
import java.util.*

class ExperienceReaderTest : BehaviorSpec({
    val experienceRepository = mockk<ExperienceRepository>(relaxed = true)
    val experienceReader = ExperienceReader(experienceRepository)

    Given("단건 조회할 경험 id가 주어졌을 때") {
        val experienceId = UUID.randomUUID()

        When("ExperienceReader.readExperience 가 호출되면") {
            experienceReader.readExperience(experienceId)

            Then("experienceRepository.findByExperienceId 가 호출된다.") {
                verify { experienceRepository.findByExperienceId(experienceId) }
            }
        }
    }

    Given("userId가 주어졌을 때") {
        val userId = UUID.randomUUID()

        When("ExperienceReader.readAllYearsByExistExperience 가 호출되면") {
            experienceReader.readAllYearsByExistExperience(userId)

            Then("ExperienceReader.readAllByUserId 가 호출된다.") {
                verify { experienceReader.readAllByUserId(userId) }
            }
        }

        When("ExperienceReader.readAllByUserId 가 호출되면") {
            experienceReader.readAllByUserId(userId)

            Then("experienceRepository.findAllByUserId 가 호출된다.") {
                verify { experienceRepository.findAllByUserId(userId) }
            }
        }
    }

    Given("userId와 year가 주어졌을 때") {
        val userId = UUID.randomUUID()
        val year = LocalDate.now().year

        When("ExperienceReader.readByUserIdAndYearDesc 가 호출되면") {
            experienceReader.readByUserIdAndYearDesc(
                year = year,
                userId = userId)

            Then("experienceRepository.findByUserIdAndYearDesc 가 호출된다.") {
                verify { experienceRepository.findByUserIdAndYearDesc(
                    year = year,
                    userId = userId)
                }
            }
        }
    }

    Given("year와 parentTagId가 주어졌을 때") {
        val parentTagId = UUID.randomUUID()
        val year = LocalDate.now().year

        When("ExperienceReader.readByYearAndParentTagId 가 호출되면") {
            experienceReader.readByYearAndParentTagId(
                year = year,
                parentTagId = parentTagId
            )

            Then("experienceRepository.findByYearAndParentTagId 가 호출된다.") {
                verify { experienceRepository.findByYearAndParentTagId(
                    year = year,
                    parentTagId = parentTagId)
                }
            }
        }
    }

    Given("userId와 parentTagId가 주어졌을 때") {
        val parentTagId = UUID.randomUUID()
        val userId = UUID.randomUUID()

        When("ExperienceReader.readByUserIdAndParentTagId 가 호출되면") {
            experienceReader.readByUserIdAndParentTagId(
                userId = userId,
                parentTagId = parentTagId
            )

            Then("experienceRepository.findByYearAndParentTagId 가 호출된다.") {
                verify { experienceRepository.findByUserIdAndParentTagId(
                    userId = userId,
                    parentTagId = parentTagId)
                }
            }
        }
    }

    Given("year와 childTagId가 주어졌을 때") {
        val childTagId = UUID.randomUUID()
        val year = LocalDate.now().year

        When("ExperienceReader.readByChildTagIdAndYear 가 호출되면") {
            experienceReader.readByChildTagIdAndYear(
                childTagId = childTagId,
                year = year
            )

            Then("experienceRepository.findByYearAndChildTagId 가 호출된다.") {
                verify { experienceRepository.findByYearAndChildTagId(
                    childTagId = childTagId,
                    year = year)
                }
            }
        }
    }

    Given("조회할 experienceId 배열이 주어졌을 때") {
        val experienceIds = arrayListOf(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
        )

        When("ExperienceReader.readByIds 가 호출되면") {
            experienceReader.readByIds(experienceIds)

            Then("experienceRepository.findByIds 가 호출된다.") {
                verify { experienceRepository.findByIds(experienceIds) }
            }
        }
    }

    Given("userId, search(경험 제목 검색) 문자열이 주어졌을 때") {
        val userId = UUID.randomUUID()
        val search = "제목 검색 문자열"

        When("ExperienceReader.readIdsByUserIdAndTitleContains 가 호출되면") {
            experienceReader.readIdsByUserIdAndTitleContains(
                userId = userId,
                search = search
            )

            Then("experienceRepository.findByUserIdAndTitleContains 가 호출된다.") {
                verify { experienceRepository.findByUserIdAndTitleContains(
                    userId = userId,
                    search = search)
                }
            }
        }
    }

    Given("userId, search(경험 내용 검색) 검색 문자열이 주어졌을 때") {
        val userId = UUID.randomUUID()
        val search = "내용 검색 문자열"

        When("ExperienceReader.readIdsByContentsContains 가 호출되면") {
            experienceReader.readIdsByContentsContains(
                userId = userId,
                search = search
            )

            Then("experienceRepository.findAllByUserId 가 호출된다.") {
                verify { experienceRepository.findAllByUserId(userId = userId)
                }
            }
        }
    }

    Given("year(경험 검색 연도)이 주어졌을 때") {
        val year = LocalDate.now().year

        When("ExperienceReader.readByYear 가 호출되면") {
            experienceReader.readByYear(year)

            Then("experienceRepository.findByYear 가 호출된다.") {
                verify { experienceRepository.findByYear(year)
                }
            }
        }
    }

    Given("childTagId가 주어졌을 때") {
        val childTagId = UUID.randomUUID()

        When("ExperienceReader.readByChildTag 가 호출되면") {
            experienceReader.readByChildTagId(childTagId)

            Then("experienceRepository.findByChildTagId 가 호출된다.") {
                verify { experienceRepository.findByChildTagId(childTagId)
                }
            }
        }
    }

    Given("태그 id 배열(상, 하위 상관 X)가  주어졌을 때") {
        val tagIds = arrayListOf(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
        )

        When("ExperienceReader.readIdsByTagIds 가 호출되면") {
            experienceReader.readIdsByTagIds(tagIds)

            Then("experienceRepository.findByTagIds 가 호출된다.") {
                verify { experienceRepository.findByTagIds(tagIds)
                }
            }
        }
    }

    Given("유저 id, 역량 키워드 id 배열이 주어졌을 때") {
        val userId = UUID.randomUUID()
        val strongPointIds = arrayListOf(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
        )

        When("ExperienceReader.readIdsByStrongPointIds 가 호출되면") {
            experienceReader.readIdsByStrongPointIds(
                userId = userId,
                strongPointIds = strongPointIds
            )

            Then("experienceRepository.findByTagIds 가 호출된다.") {
                verify { experienceRepository.findAllByUserId(userId)
                }
            }
        }
    }
})
