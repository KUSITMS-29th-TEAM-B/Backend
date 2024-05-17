package com.bamyanggang.apimodule.domain.experience.presentation

import com.bamyanggang.apimodule.BaseRestDocsTest
import com.bamyanggang.apimodule.domain.experience.application.dto.CreateExperience
import com.bamyanggang.apimodule.domain.experience.application.service.ExperienceCreateService
import com.bamyanggang.commonmodule.exception.ExceptionHandler
import com.bamyanggang.commonmodule.fixture.generateFixture
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ExperienceController::class)
@Import(ExceptionHandler::class)
class ExperienceControllerTest : BaseRestDocsTest() {

    @MockBean
    private lateinit var experienceCreateService: ExperienceCreateService

    @Test
    @DisplayName("경험을 등록한다.")
    fun createExperienceTest() {
        //given
        val createExperienceRequest : CreateExperience.Request = generateFixture()

        val createExperienceResponse : CreateExperience.Response = generateFixture()

        given(experienceCreateService.createExperience(createExperienceRequest)).willReturn(createExperienceResponse)

        val request = RestDocumentationRequestBuilders.post(ExperienceApi.BASE_URL)
            .header("Authorization", "Bearer Access Token")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createExperienceRequest))

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isOk).andDo(
            resultHandler.document(
                requestHeaders(
                    headerWithName("Authorization").description("엑세스 토큰")
                ),
                requestFields(
                    fieldWithPath("title").description("경험 제목"),
                    fieldWithPath("contents").description("경험 내용"),
                    fieldWithPath("contents[].question").description("경험 내용 질문"),
                    fieldWithPath("contents[].answer").description("경험 내용 답변"),
                    fieldWithPath("strongPointIds").description("관련된 역량 키워드"),
                    fieldWithPath("parentTagId").description("속한 상위 태그"),
                    fieldWithPath("childTagId").description("속한 하위 태그"),
                    fieldWithPath("startedAt").description("경험 시작 날짜"),
                    fieldWithPath("endedAt").description("경험 종료 날짜"),
                ),
                responseFields(
                    fieldWithPath("id").description("경험 id")
                )
            )
        )
    }
}

