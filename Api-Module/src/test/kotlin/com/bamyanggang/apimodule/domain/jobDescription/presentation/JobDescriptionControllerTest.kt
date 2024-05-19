package com.bamyanggang.apimodule.domain.jobDescription.presentation

import com.bamyanggang.apimodule.BaseRestDocsTest
import com.bamyanggang.apimodule.common.dto.PageResponse
import com.bamyanggang.apimodule.domain.jobDescription.application.dto.*
import com.bamyanggang.apimodule.domain.jobDescription.application.service.ApplyCreateService
import com.bamyanggang.apimodule.domain.jobDescription.application.service.ApplyInfoGetService
import com.bamyanggang.apimodule.domain.jobDescription.application.service.JobDescriptionCreateService
import com.bamyanggang.apimodule.domain.jobDescription.application.service.JobDescriptionInfoGetService
import com.bamyanggang.commonmodule.exception.ExceptionHandler
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.common.pagination.PageDomain
import com.bamyanggang.domainmodule.domain.jobDescription.enums.SortType
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import java.util.*

@WebMvcTest(JobDescriptionController::class)
@Import(ExceptionHandler::class)
class JobDescriptionControllerTest : BaseRestDocsTest() {

    @MockBean
    private lateinit var jobDescriptionCreateService: JobDescriptionCreateService

    @MockBean
    private lateinit var applyCreateService: ApplyCreateService

    @MockBean
    private lateinit var jobDescriptionInfoGetService: JobDescriptionInfoGetService

    @MockBean
    private lateinit var applyInfoGetService: ApplyInfoGetService

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

        given(jobDescriptionCreateService.createJobDescription(createJobDescriptionRequest)).willReturn(
            createJobDescriptionResponse
        )

        val request = RestDocumentationRequestBuilders.post(JobDescriptionApi.BASE_URL)
            .header("Authorization", "Bearer Access Token")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createJobDescriptionRequest))
        //when
        val result = mockMvc.perform(request)
        //then
        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("엑세스 토큰")
                    ),
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

        given(jobDescriptionCreateService.createJobDescription(createJobDescriptionRequest)).willThrow(
            IllegalArgumentException("내용은 필수입니다.")
        )

        val request = RestDocumentationRequestBuilders.post(JobDescriptionApi.BASE_URL)
            .header("Authorization", "Bearer Access Token")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createJobDescriptionRequest))

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isBadRequest)
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("엑세스 토큰")
                    ),
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

        given(jobDescriptionCreateService.createJobDescription(createJobDescriptionRequest)).willThrow(
            IllegalArgumentException("시작일은 종료일보다 빨라야 합니다.")
        )

        val request = RestDocumentationRequestBuilders.post(JobDescriptionApi.BASE_URL)
            .header("Authorization", "Bearer Access Token")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createJobDescriptionRequest))

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isBadRequest)
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("엑세스 토큰")
                    ),
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
            it.set("contents", listOf(createApplyContentRequest))
        }
        val request = RestDocumentationRequestBuilders.post(JobDescriptionApi.APPLY, jobDescriptionId)
            .header("Authorization", "Bearer Access Token")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createApplyRequest))
        //when
        val result = mockMvc.perform(request)
        //then
        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("엑세스 토큰")
                    ),
                    pathParameters(
                        RequestDocumentation.parameterWithName("jobDescriptionId").description("jd 공고 ID")
                    ),
                    requestFields(
                        fieldWithPath("contents").description("내용"),
                        fieldWithPath("contents[].question").description("질문"),
                        fieldWithPath("contents[].answer").description("답변")
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
            it.set("contents", listOf(createApplyContentRequest))
        }
        val request = RestDocumentationRequestBuilders.post(JobDescriptionApi.APPLY, jobDescriptionId)
            .header("Authorization", "Bearer Access Token")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createApplyRequest))
        given(applyCreateService.createApply(createApplyRequest, jobDescriptionId)).willThrow(IllegalArgumentException("답변은 필수입니다."))

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isBadRequest)
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("엑세스 토큰")
                    ),
                    pathParameters(
                        RequestDocumentation.parameterWithName("jobDescriptionId").description("jd 공고 ID")
                    ),
                    responseFields(
                        fieldWithPath("code").description("에러 코드"),
                        fieldWithPath("message").description("에러 메시지")
                    )
                )
            )
    }

    @Test
    @DisplayName("JD 공고를 조회한다")
    fun getJobDescription() {
        // given
        val pageRequest = PageRequest.of(0, 6)
        val getJobDescriptionInfoResponse: GetJobDescriptionInfo.Response.Basic = generateFixture {
            it.set("jobDescriptionId", UUID.randomUUID())
            it.set("remainingDate", 1)
            it.set("enterpriseName", "기업 이름")
            it.set("title", "직무 공고 제목")
            it.set("writeStatus", WriteStatus.WRITING)
            it.set("createdAt", LocalDateTime.now())
            it.set("startedAt", LocalDateTime.now())
            it.set("endedAt", LocalDateTime.now()) }

        val slice = PageDomain(listOf(getJobDescriptionInfoResponse), 0, 1, 5, true)
        val pageResponse = PageResponse.from(slice)
        given(jobDescriptionInfoGetService.getJobDescriptionInfo(pageRequest, WriteStatus.WRITING, SortType.ENDED)).willReturn(pageResponse)


        val request = RestDocumentationRequestBuilders.get(JobDescriptionApi.BASE_URL)
            .header("Authorization", "Bearer Access Token")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .queryParam("page", pageRequest.pageNumber.toString())
            .queryParam("size", pageRequest.pageSize.toString())
            .queryParam("writeStatus", WriteStatus.WRITING.toString())
            .queryParam("sortType", SortType.ENDED.toString())

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("엑세스 토큰")
                    ),
                    RequestDocumentation.queryParameters(
                        RequestDocumentation.parameterWithName("page").description("요청 페이지"),
                        RequestDocumentation.parameterWithName("size").description("요청 사이즈"),
                        RequestDocumentation.parameterWithName("writeStatus")
                            .description("작성 상태. NOT_APPLIED(칩 없음, 작성 전), WRITING(작성 중), WRITTEN(작성 완료), CLOSED(마감)"),
                        RequestDocumentation.parameterWithName("sortType")
                            .description("정렬 타입 CREATED(등록순), ENDED(마감순)"),
                    ),
                    responseFields(
                        fieldWithPath("content[].jobDescriptionId").description("직무 공고 ID"),
                        fieldWithPath("content[].remainingDate").description("디데이"),
                        fieldWithPath("content[].enterpriseName").description("기업 이름"),
                        fieldWithPath("content[].title").description("직무 공고 제목"),
                        fieldWithPath("content[].writeStatus").description("작성 상태. NOT_APPLIED(칩 없음, 작성 전), WRITING(작성 중), WRITTEN(작성 완료), CLOSED(마감)"),
                        fieldWithPath("content[].createdAt").description("생성일"),
                        fieldWithPath("content[].startedAt").description("시작일"),
                        fieldWithPath("content[].endedAt").description("종료일"),
                        fieldWithPath("page").description("요청 페이지"),
                        fieldWithPath("size").description("요청 사이즈"),
                        fieldWithPath("totalPage").description("전체 페이지 수"),
                        fieldWithPath("hasNext").description("다음 데이터 존재 여부")
                    )
                )
            )
    }

    @Test
    @DisplayName("JD 공고 상세를 조회한다")
    fun getJobDescriptionDetail() {
        // given
        val jobDescriptionId = UUID.randomUUID()
        val getJobDescriptionInfoResponse: GetJobDescriptionInfo.Response.Detail = generateFixture {
            it.set("enterpriseName", "기업 이름")
            it.set("title", "직무 공고 제목")
            it.set("content", "직무 공고 내용")
            it.set("link", "직무 공고 링크")
            it.set("startedAt", LocalDateTime.now())
            it.set("endedAt", LocalDateTime.now())
        }

        given(jobDescriptionInfoGetService.getJobDescriptionDetail(jobDescriptionId)).willReturn(getJobDescriptionInfoResponse)

        val request = RestDocumentationRequestBuilders.get(JobDescriptionApi.DETAIL, jobDescriptionId)
            .header("Authorization", "Bearer Access Token")
            .contentType(MediaType.APPLICATION_JSON_VALUE)

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("엑세스 토큰")
                    ),
                    pathParameters(
                        RequestDocumentation.parameterWithName("jobDescriptionId").description("jd 공고 ID")
                    ),
                    responseFields(
                        fieldWithPath("enterpriseName").description("기업 이름"),
                        fieldWithPath("title").description("직무 공고 제목"),
                        fieldWithPath("content").description("직무 공고 내용"),
                        fieldWithPath("link").description("직무 공고 링크"),
                        fieldWithPath("startedAt").description("시작일"),
                        fieldWithPath("endedAt").description("종료일")
                    )
                )
            )
    }

    @Test
    @DisplayName("JD 공고 자기소개서 정보를 조회한다")
    fun getApplyInfo() {
        // given
        val jobDescriptionId = UUID.randomUUID()
        val contentInfo: GetApplyInfo.ContentInfo = generateFixture {
            it.set("question", "질문")
            it.set("answer", "답변")
        }
        val getApplyInfoResponse: GetApplyInfo.Response = generateFixture {
            it.set("applyContentList", listOf(contentInfo))
        }

        given(applyInfoGetService.getApplyInfo(jobDescriptionId)).willReturn(getApplyInfoResponse)

        val request = RestDocumentationRequestBuilders.get(JobDescriptionApi.APPLY, jobDescriptionId)
            .header("Authorization", "Bearer Access Token")
            .contentType(MediaType.APPLICATION_JSON_VALUE)

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("엑세스 토큰")
                    ),
                    pathParameters(
                        RequestDocumentation.parameterWithName("jobDescriptionId").description("jd 공고 ID")
                    ),
                    responseFields(
                        fieldWithPath("applyContentList[].question").description("질문"),
                        fieldWithPath("applyContentList[].answer").description("답변")
                    )
                )
            )
    }

}
