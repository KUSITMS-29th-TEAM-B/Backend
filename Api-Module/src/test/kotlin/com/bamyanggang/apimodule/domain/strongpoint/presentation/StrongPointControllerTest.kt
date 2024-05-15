package com.bamyanggang.apimodule.domain.strongpoint.presentation

import com.bamyanggang.apimodule.BaseRestDocsTest
import com.bamyanggang.apimodule.domain.experience.application.dto.CreateStrongPointRequest
import com.bamyanggang.apimodule.domain.experience.application.dto.CreateStrongPointResponse
import com.bamyanggang.apimodule.domain.experience.presentation.StrongPointApi
import com.bamyanggang.apimodule.domain.experience.presentation.StrongPointController
import com.bamyanggang.commonmodule.fixture.generateFixture
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(StrongPointController::class)
class StrongPointControllerTest : BaseRestDocsTest() {

    @MockBean
    private lateinit var strongPointController: StrongPointController

    @Test
    @DisplayName("역량 키워드를 저장한 뒤 생성된 역량 키워드 ID를 반환한다.")
    fun createStrongPointTest() {
        val createStrongPointRequest: CreateStrongPointRequest = generateFixture()
        val createStrongPointResponse: CreateStrongPointResponse = generateFixture()

        given(strongPointController.createStrongPoint(createStrongPointRequest)).willReturn(createStrongPointResponse)

        val request = RestDocumentationRequestBuilders.post(StrongPointApi.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createStrongPointRequest))

        val result = mockMvc.perform(request)

        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestFields(
                        fieldWithPath("name").description("역량 키워드 이름"),
                    ),
                    responseFields(
                        fieldWithPath("id").description("생성된 역량 키워드 id"),
                    )
                )
            )
    }
}