package com.bamyanggang.apimodule.domain.strongpoint.presentation

import com.bamyanggang.apimodule.BaseRestDocsTest
import com.bamyanggang.apimodule.domain.strongpoint.application.dto.CreateStrongPoint
import com.bamyanggang.apimodule.domain.strongpoint.application.dto.GetStrongPoint
import com.bamyanggang.commonmodule.exception.ExceptionHandler
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.strongpoint.exception.StrongPointException
import com.bamyanggang.domainmodule.domain.strongpoint.exception.StrongPointExceptionMessage
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
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest(StrongPointController::class)
@Import(ExceptionHandler::class)
class StrongPointControllerTest : BaseRestDocsTest() {

    @MockBean
    private lateinit var strongPointController: StrongPointController

    @Test
    @DisplayName("역량 키워드를 저장한 뒤 생성된 역량 키워드 ID를 반환한다.")
    fun createStrongPointTest() {
        val createStrongPoint: CreateStrongPoint.Request = generateFixture()
        val createStrongPointResponse: CreateStrongPoint.Response = generateFixture()

        given(strongPointController.createStrongPoint(createStrongPoint)).willReturn(createStrongPointResponse)

        val request = RestDocumentationRequestBuilders.post(StrongPointApi.BASE_URL)
            .header("Authorization", "Bearer AccessToken")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createStrongPoint))

        val result = mockMvc.perform(request)

        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("엑세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("name").description("역량 키워드 이름"),
                    ),
                    responseFields(
                        fieldWithPath("id").description("생성된 역량 키워드 id"),
                    )
                )
            )
    }

    @Test
    @DisplayName("역량 키워드 등록 시 역량 키워드 이름이 비어있다면 예외를 반환한다.")
    fun createStrongPointNameEmptyTest() {
        val createStrongPoint: CreateStrongPoint.Request = generateFixture {
            it.set("name", "")
        }

        given(strongPointController.createStrongPoint(createStrongPoint)).willThrow(IllegalArgumentException(
            StrongPointExceptionMessage.NAME_NOT_EMPTY.message))

        val request = RestDocumentationRequestBuilders.post(StrongPointApi.BASE_URL)
            .header("Authorization", "Bearer AccessToken")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createStrongPoint))

        val result = mockMvc.perform(request)

        result.andExpect(status().isBadRequest)
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("엑세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("name").description("역량 키워드 이름"),
                    ),
                    responseFields(
                        fieldWithPath("code").description(HttpStatus.BAD_REQUEST),
                        fieldWithPath("message").description(StrongPointExceptionMessage.NAME_NOT_EMPTY.message),
                    )
                )
            )
    }

    @Test
    @DisplayName("중복된 역량 키워드 등록 시 등록하지 않고 예외를 반환한다.")
    fun duplicatedStrongPointNameTest() {
        val duplicatedRequest = CreateStrongPoint.Request("duplicatedName")

        given(strongPointController.createStrongPoint(duplicatedRequest)).willThrow(
            IllegalArgumentException(StrongPointExceptionMessage.DUPLICATED_NAME.message))

        val request = RestDocumentationRequestBuilders.post(StrongPointApi.BASE_URL)
            .header("Authorization", "Bearer AccessToken")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(duplicatedRequest))

        val result = mockMvc.perform(request)

        result.andExpect(status().isBadRequest)
            .andDo(resultHandler.document(
                requestHeaders(
                    headerWithName("Authorization").description("엑세스 토큰")
                ),
                requestFields(
                    fieldWithPath("name").description("역량 키워드 이름"),
                ),
                responseFields(
                    fieldWithPath("code").description(HttpStatus.BAD_REQUEST),
                    fieldWithPath("message").description(StrongPointExceptionMessage.DUPLICATED_NAME.message),
                )
            )
        )
    }

    @Test
    @DisplayName("역량 키워드 개수 제한보다 더 많은 키워드 등록 시도 시 예외를 반환한다.")
    fun overCountLimitTest(){
        val overCountLimitRequest: CreateStrongPoint.Request = generateFixture()

        given(strongPointController.createStrongPoint(overCountLimitRequest)).willThrow(StrongPointException.OverCountLimit())

        val request = RestDocumentationRequestBuilders.post(StrongPointApi.BASE_URL)
            .header("Authorization", "Bearer AccessToken")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(overCountLimitRequest))

        val result = mockMvc.perform(request)

        result.andExpect(status().isBadRequest)
            .andDo(resultHandler.document(
                requestHeaders(
                    headerWithName("Authorization").description("엑세스 토큰")
                ),
                requestFields(
                    fieldWithPath("name").description("역량 키워드 이름"),
                ),
                responseFields(
                    fieldWithPath("code").description(StrongPointException.OverCountLimit().code),
                    fieldWithPath("message").description(StrongPointException.OverCountLimit().message),
                )
            )
        )
    }


    @Test
    @DisplayName("역량 키워드를 삭제한다.")
    fun deleteStrongPointTest() {
        val strongPointId: UUID = generateFixture()

        val request = RestDocumentationRequestBuilders.delete(StrongPointApi.STRONG_POINT_PATH_VARIABLE_URL, strongPointId)
            .header("Authorization", "Bearer AccessToken")

        val result = mockMvc.perform(request)

        result.andExpect(status().isOk)
            .andDo(resultHandler.document(
                requestHeaders(
                  headerWithName("Authorization").description("엑세스 토큰")
                ),
                pathParameters(
                    parameterWithName("strongPointId").description("역량 키워드 id")
                )
            )
        )
    }

    @Test
    @DisplayName("존재하지 않는 역량 키워드 삭제 시도 시 예외를 반환한다.")
    fun deleteNotFoundStrongPointTest() {
        val notExistStrongPointId: UUID = generateFixture()

        given(strongPointController.deleteStrongPoint(notExistStrongPointId)).willThrow(
            IllegalArgumentException(StrongPointExceptionMessage.NOT_FOUND_STRONG_POINT.message))

        val request = RestDocumentationRequestBuilders.delete(StrongPointApi.STRONG_POINT_PATH_VARIABLE_URL, notExistStrongPointId)
            .header("Authorization", "Bearer AccessToken")

        val result = mockMvc.perform(request)

        result.andExpect(status().isBadRequest)
            .andDo(resultHandler.document(
                requestHeaders(
                    headerWithName("Authorization").description("엑세스 토큰")
                ),
                pathParameters(
                    parameterWithName("strongPointId").description("역량 키워드 id")
                ),
                responseFields(
                    fieldWithPath("code").description(HttpStatus.NOT_FOUND),
                    fieldWithPath("message").description(StrongPointExceptionMessage.NOT_FOUND_STRONG_POINT.message)
                )
            )
        )
    }

    @Test
    @DisplayName("유저가 등록한 역량 키워드를 전체 조회한다.")
    fun getAllStrongPointTest() {

        val strongPointInfo1 = GetStrongPoint.StrongPointInfo(generateFixture<UUID>(), "역량 키워드 1")
        val strongPointInfo2 = GetStrongPoint.StrongPointInfo(generateFixture<UUID>(), "역량 키워드 2")

        val strongPointInfos = arrayListOf(strongPointInfo1, strongPointInfo2)
        val getStrongPointResponse = GetStrongPoint.Response(strongPointInfos.size, strongPointInfos)

        given(strongPointController.getAllStrongPoints()).willReturn(getStrongPointResponse)

        val request = RestDocumentationRequestBuilders.get(StrongPointApi.BASE_URL)
            .header("Authorization", "Bearer AccessToken")

        val result = mockMvc.perform(request)

        result.andExpect(status().isOk)
            .andDo(resultHandler.document(
                requestHeaders(
                    headerWithName("Authorization").description("엑세스 토큰")
                ),
                responseFields(
                    fieldWithPath("count").description("역량 키워드 개수"),
                    fieldWithPath("strongPoints[].id").description("역량 키워드 id"),
                    fieldWithPath("strongPoints[].name").description("역량 키워드 이름"),
                )
            )
        )
    }
}
