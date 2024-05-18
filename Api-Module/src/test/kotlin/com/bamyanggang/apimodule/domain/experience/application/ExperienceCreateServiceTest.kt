//package com.bamyanggang.apimodule.domain.experience.application
//
//import com.bamyanggang.apimodule.domain.experience.application.dto.CreateExperience
//import com.bamyanggang.apimodule.domain.experience.application.service.ExperienceCreateService
//import com.bamyanggang.commonmodule.fixture.generateFixture
//import com.bamyanggang.domainmodule.domain.experience.service.ExperienceAppender
//import com.bamyanggang.domainmodule.domain.experience.service.ExperienceContentAppender
//import io.kotest.core.spec.style.BehaviorSpec
//import io.mockk.every
//import io.mockk.mockk
//import io.mockk.verify
//import java.util.*
//
//class ExperienceCreateServiceTest : BehaviorSpec({
//
//    val experienceAppender: ExperienceAppender = mockk()
//    val experienceContentAppender: ExperienceContentAppender = mockk()
//    val createExperienceService = ExperienceCreateService(experienceAppender, experienceContentAppender)
//
//    Given("등록할 경험 Request과 응답이 주어지면") {
//        val request : CreateExperience.Request = generateFixture()
//        val response : CreateExperience.Response = generateFixture()
//
//        When("ExperienceCreateService.createExperience가 호출 되었을 때" ) {
//            every { createExperienceService.createExperience(request) } returns response
//
//            Then("experienceAppender.appendExperience가 호출된다.") {
//                verify {
//                    experienceAppender.appendExperience(
//                        request.title,
//                        generateFixture<UUID>(),
//                        request.startedAt,
//                        request.endedAt
//                    )
//                }
//            }
//
//            Then("experienceContentAppender.appendExperienceContent가 호출된다.") {
//                verify {
//                    experienceContentAppender.appendExperienceContent(
//                        generateFixture(),
//                        generateFixture(),
//                        generateFixture(),
//                    )
//                }
//            }
//        }
//    }
//})
