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
    @DisplayName("역량 키워드를 저장한 뒤 생성된 역량 키워드 정보를 반환한다.")
    fun createStrongPointTest() {
        val createStrongPoint = CreateStrongPoint.Request(
            arrayListOf(
                CreateStrongPoint.StrongPointName("이름 1"),
                CreateStrongPoint.StrongPointName("이름 2"),
            ))

        val strongPoints = arrayListOf(
            CreateStrongPoint.DetailStrongPoint(generateFixture(), "역량 키워드 이름 1"),
            CreateStrongPoint.DetailStrongPoint(generateFixture(), "역량 키워드 이름 2")
        )

        val createStrongPointResponse: CreateStrongPoint.Response = CreateStrongPoint.Response(strongPoints)

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
                        fieldWithPath("names").description("역량 키워드 이름 배열"),
                        fieldWithPath("names[].name").description("역량 키워드 이름"),
                    ),
                    responseFields(
                        fieldWithPath("strongPoints").description("생성된 역량 키워드 배열"),
                        fieldWithPath("strongPoints[].id").description("생성된 역량 키워드 id"),
                        fieldWithPath("strongPoints[].name").description("생성된 역량 키워드 이름"),
                    )
                )
            )
    }
    
    @Test
    @DisplayName("역량 키워드 등록 시 역량 키워드 이름이 비어있다면 예외를 반환한다.")
    fun createStrongPointNameEmptyTest() {
        val strongPointNames = arrayListOf(
            CreateStrongPoint.StrongPointName(""),
            CreateStrongPoint.StrongPointName("공백 아님")
        )

        val createStrongPoint: CreateStrongPoint.Request = generateFixture {
            it.set("names", strongPointNames)
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
                        fieldWithPath("names").description("역량 키워드 이름 배열"),
                        fieldWithPath("names[].name").description("역량 키워드 이름"),
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
        val duplicatedNames = arrayListOf(
            CreateStrongPoint.StrongPointName("DuplicatedName"),
        )

        val duplicatedRequest = CreateStrongPoint.Request(duplicatedNames)

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
                    fieldWithPath("names").description("역량 키워드 이름 배열"),
                    fieldWithPath("names[].name").description("역량 키워드 이름"),
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
        val overCountLimitRequest = CreateStrongPoint.Request(
            arrayListOf(
                CreateStrongPoint.StrongPointName("역량 키워드 이름 1"),
                CreateStrongPoint.StrongPointName("역량 키워드 이름 2")
            )
        )


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
                    fieldWithPath("names").description("역량 키워드 이름 배열"),
                    fieldWithPath("names[].name").description("역량 키워드 이름"),
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

        val detailStrongPoint1 = GetStrongPoint.DetailStrongPoint(generateFixture<UUID>(), "역량 키워드 1")
        val detailStrongPoint2 = GetStrongPoint.DetailStrongPoint(generateFixture<UUID>(), "역량 키워드 2")

        val strongPointInfos = arrayListOf(detailStrongPoint1, detailStrongPoint2)
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
