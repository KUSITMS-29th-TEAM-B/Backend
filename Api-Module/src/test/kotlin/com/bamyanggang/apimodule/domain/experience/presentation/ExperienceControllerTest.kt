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
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import java.util.*

@WebMvcTest(ExperienceController::class)
@Import(ExceptionHandler::class)
class ExperienceControllerTest : BaseRestDocsTest() {

    @MockBean
    private lateinit var experienceCreateService: ExperienceCreateService

    @Test
    @DisplayName("경험을 등록한다.")
    fun createExperienceTest() {
        //given
        val content1 = CreateExperience.ExperienceContentRequest("질문1", "답변1")
        val content2 = CreateExperience.ExperienceContentRequest("질문2", "답변2")

        val contentRequest = arrayListOf(content1, content2)

        val createExperienceRequest : CreateExperience.Request = generateFixture {
            it.set("title", "제목")
            it.set("contents", contentRequest)
            it.set("strongPointIds", generateFixture<List<UUID>>())
            it.set("parentTagId", generateFixture<UUID>())
            it.set("childTagId", generateFixture<UUID>())
            it.set("startedAt", generateFixture<LocalDateTime>())
            it.set("endedAt", generateFixture<LocalDateTime>())
        }

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

    @Test
    @DisplayName("제목 길이가 50이 넘으면 예외가 발생한다.")
    fun createOverTitleLengthTest() {
        //given
        val content1 = CreateExperience.ExperienceContentRequest("질문1", "답변1")
        val content2 = CreateExperience.ExperienceContentRequest("질문2", "답변2")

        val contentRequest = arrayListOf(content1, content2)

        val createExperienceRequest : CreateExperience.Request = generateFixture {
            it.set("title", "제목")
            it.set("contents", contentRequest)
            it.set("strongPointIds", generateFixture<List<UUID>>())
            it.set("parentTagId", generateFixture<UUID>())
            it.set("childTagId", generateFixture<UUID>())
            it.set("startedAt", generateFixture<LocalDateTime>())
            it.set("endedAt", generateFixture<LocalDateTime>())
        }

        given(experienceCreateService.createExperience(createExperienceRequest)).willThrow(IllegalArgumentException("제목의 글자 수는 50자 제한입니다."))

        val request = RestDocumentationRequestBuilders.post(ExperienceApi.BASE_URL)
            .header("Authorization", "Bearer Access Token")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createExperienceRequest))

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isBadRequest).andDo(
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
                    fieldWithPath("code").description(HttpStatus.BAD_REQUEST),
                    fieldWithPath("message").description("제목의 글자 수는 50자 제한입니다.")
                )
            )
        )
    }

    @Test
    @DisplayName("역량 키워드를 5개 초과하여 등록할 경우 예외를 반환한다.")
    fun createOverStrongPointCountTest() {
        //given
        val content1 = CreateExperience.ExperienceContentRequest("질문1", "답변1")
        val content2 = CreateExperience.ExperienceContentRequest("질문2", "답변2")

        val contentRequest = arrayListOf(content1, content2)

        val createExperienceRequest : CreateExperience.Request = generateFixture {
            it.set("title", "제목")
            it.set("contents", contentRequest)
            it.set("strongPointIds", generateFixture<List<UUID>>())
            it.set("parentTagId", generateFixture<UUID>())
            it.set("childTagId", generateFixture<UUID>())
            it.set("startedAt", generateFixture<LocalDateTime>())
            it.set("endedAt", generateFixture<LocalDateTime>())
        }

        given(experienceCreateService.createExperience(createExperienceRequest)).willThrow(IllegalArgumentException("역량 키워드는 최대 5개까지 붙일 수 있습니다."))

        val request = RestDocumentationRequestBuilders.post(ExperienceApi.BASE_URL)
            .header("Authorization", "Bearer Access Token")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createExperienceRequest))

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isBadRequest).andDo(
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
                    fieldWithPath("code").description(HttpStatus.BAD_REQUEST),
                    fieldWithPath("message").description("역량 키워드는 최대 5개까지 붙일 수 있습니다.")
                )
            )
        )
    }
}
