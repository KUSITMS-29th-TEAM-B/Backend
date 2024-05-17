package com.bamyanggang.apimodule.domain.jobDescription.presentation

import com.bamyanggang.apimodule.BaseRestDocsTest
import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateApply
import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateApplyContent
import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateJobDescription
import com.bamyanggang.apimodule.domain.jobDescription.application.service.ApplyCreateService
import com.bamyanggang.apimodule.domain.jobDescription.application.service.JobDescriptionCreateService
import com.bamyanggang.commonmodule.exception.ExceptionHandler
import com.bamyanggang.commonmodule.fixture.generateFixture
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.http.MediaType
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import java.util.UUID

@WebMvcTest(JobDescriptionController::class)
@Import(ExceptionHandler::class)
class JobDescriptionControllerTest : BaseRestDocsTest() {

    @MockBean
    private lateinit var jobDescriptionCreateService: JobDescriptionCreateService
    @MockBean
    private lateinit var applyCreateService: ApplyCreateService

    @Test
    @DisplayName("직무 공고를 등록한다.")
    fun createJobDescription() {
        //given
        val createJobDescriptionRequest: CreateJobDescription.Request = generateFixture {
            it.set("enterpriseName", "기업 이름")
            it.set("title", "직무 공고 제목")
            it.set("content", "직무 공고 내용")
            it.set("link", "직무 공고 링크")
            it.set("startedAt", LocalDateTime.now())
            it.set("endedAt", LocalDateTime.now())
        }
        val createJobDescriptionResponse: CreateJobDescription.Response = generateFixture {
            it.set("jobDescriptionId", UUID.randomUUID())
        }
        val request = RestDocumentationRequestBuilders.post(JobDescriptionApi.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createJobDescriptionRequest))
        given(jobDescriptionCreateService.createJobDescription(createJobDescriptionRequest)).willReturn(createJobDescriptionResponse)
        //when
        val result = mockMvc.perform(request)
        //then
        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestFields(
                        fieldWithPath("enterpriseName").description("기업 이름"),
                        fieldWithPath("title").description("직무 공고 제목"),
                        fieldWithPath("content").description("직무 공고 내용"),
                        fieldWithPath("link").description("직무 공고 링크"),
                        fieldWithPath("startedAt").description("직무 공고 시작일"),
                        fieldWithPath("endedAt").description("직무 공고 종료일")
                    ),
                    responseFields(
                        fieldWithPath("jobDescriptionId").description("직무 공고 ID")
                    )
                )
            )
    }

    @Test
    @DisplayName("직무 공고를 등록시, 빈 값을 보내면 예외를 반환한다")
    fun createJobDescriptionWithEmptyValue() {
        //given
        val createJobDescriptionRequest: CreateJobDescription.Request = generateFixture {
            it.set("enterpriseName", "기업 이름")
            it.set("title", "직무 공고 제목")
            it.set("content", "")
            it.set("link", "직무 공고 링크")
            it.set("startedAt", LocalDateTime.now())
            it.set("endedAt", LocalDateTime.now())
        }
        val request = RestDocumentationRequestBuilders.post(JobDescriptionApi.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createJobDescriptionRequest))
        given(jobDescriptionCreateService.createJobDescription(createJobDescriptionRequest)).willThrow(IllegalArgumentException("내용은 필수입니다."))

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isBadRequest)
            .andDo(
                resultHandler.document(
                    responseFields(
                        fieldWithPath("code").description("에러 코드"),
                        fieldWithPath("message").description("에러 메시지")
                    )
                )
            )
    }

    @Test
    @DisplayName("직무 공고를 등록시, 시작일이 종료일보다 늦으면 예외를 반환한다")
    fun createJobDescriptionWithInvalidDate() {
        //given
        val createJobDescriptionRequest: CreateJobDescription.Request = generateFixture {
            it.set("enterpriseName", "기업 이름")
            it.set("title", "직무 공고 제목")
            it.set("content", "직무 공고 내용")
            it.set("link", "직무 공고 링크")
            it.set("startedAt", LocalDateTime.now())
            it.set("endedAt", LocalDateTime.now().minusDays(1))
        }
        val request = RestDocumentationRequestBuilders.post(JobDescriptionApi.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createJobDescriptionRequest))
        given(jobDescriptionCreateService.createJobDescription(createJobDescriptionRequest)).willThrow(IllegalArgumentException("시작일은 종료일보다 빨라야 합니다."))

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isBadRequest)
            .andDo(
                resultHandler.document(
                    responseFields(
                        fieldWithPath("code").description("에러 코드"),
                        fieldWithPath("message").description("에러 메시지")
                    )
                )
            )
    }

    @Test
    @DisplayName("자기소개서를 등록한다.")
    fun createApply() {
        //given
        val jobDescriptionId = UUID.randomUUID()
        val createApplyContentRequest: CreateApplyContent = generateFixture {
            it.set("question", "질문")
            it.set("answer", "답변")
        }
        val createApplyRequest: CreateApply.Request = generateFixture {
            it.set("title", "제목")
            it.set("contents", listOf(createApplyContentRequest))
        }
        val request = RestDocumentationRequestBuilders.post(JobDescriptionApi.APPLY, jobDescriptionId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createApplyRequest))
        //when
        val result = mockMvc.perform(request)
        //then
        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestFields(
                        fieldWithPath("title").description("제목"),
                        fieldWithPath("contents").description("내용"),
                        fieldWithPath("contents[].question").description("질문"),
                        fieldWithPath("contents[].answer").description("답변")
                    )
                )
            )
    }

    @Test
    @DisplayName("자기소개서를 등록시, 제목이 비어있으면 에러를 반환한다")
    fun createApplyWithEmptyTitle() {
        //given
        val jobDescriptionId = UUID.randomUUID()
        val createApplyContentRequest: CreateApplyContent = generateFixture {
            it.set("question", "질문")
            it.set("answer", "답변")
        }
        val createApplyRequest: CreateApply.Request = generateFixture {
            it.set("title", "")
            it.set("contents", listOf(createApplyContentRequest))
        }
        val request = RestDocumentationRequestBuilders.post(JobDescriptionApi.APPLY, jobDescriptionId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createApplyRequest))
        given(applyCreateService.createApply(createApplyRequest, jobDescriptionId)).willThrow(IllegalArgumentException("제목은 필수입니다."))

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isBadRequest)
            .andDo(
                resultHandler.document(
                    responseFields(
                        fieldWithPath("code").description("에러 코드"),
                        fieldWithPath("message").description("에러 메시지")
                    )
                )
            )
    }

    @Test
    @DisplayName("자기소개서를 등록시, 내용이 비어있으면 에러를 반환한다")
    fun createApplyWithEmptyContent() {
        //given
        val jobDescriptionId = UUID.randomUUID()
        val createApplyContentRequest: CreateApplyContent = generateFixture {
            it.set("question", "질문")
            it.set("answer", "")
        }
        val createApplyRequest: CreateApply.Request = generateFixture {
            it.set("title", "제목")
            it.set("contents", listOf(createApplyContentRequest))
        }
        val request = RestDocumentationRequestBuilders.post(JobDescriptionApi.APPLY, jobDescriptionId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createApplyRequest))
        given(applyCreateService.createApply(createApplyRequest, jobDescriptionId)).willThrow(IllegalArgumentException("답변은 필수입니다."))

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isBadRequest)
            .andDo(
                resultHandler.document(
                    responseFields(
                        fieldWithPath("code").description("에러 코드"),
                        fieldWithPath("message").description("에러 메시지")
                    )
                )
            )
    }

}
