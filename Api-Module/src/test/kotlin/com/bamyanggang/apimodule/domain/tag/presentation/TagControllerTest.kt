package com.bamyanggang.apimodule.domain.tag.presentation

import com.bamyanggang.apimodule.BaseRestDocsTest
import com.bamyanggang.apimodule.domain.tag.application.dto.CreateTag
import com.bamyanggang.commonmodule.fixture.generateFixture
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest(TagController::class)
class TagControllerTest : BaseRestDocsTest() {

    @MockBean
    private lateinit var tagController: TagController

    @Test
    @DisplayName("상위 태그를 등록한다.")
    fun createParentTagTest() {
        //given
        val createTagRequest: CreateTag.Request = generateFixture()
        val createTagResponse: CreateTag.Response = generateFixture()

        given(tagController.createTag(null, createTagRequest)).willReturn(createTagResponse)

        val request = RestDocumentationRequestBuilders.post(TagApi.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createTagRequest))

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isOk)
            .andDo(resultHandler.document(
                requestFields(
                    fieldWithPath("name").description("태그 이름")
                ),
                responseFields(
                    fieldWithPath("id").description("생성된 태그 id")
                )
            )
        )
    }

    @Test
    @DisplayName("하위 태그를 등록한다.")
    fun createChildTagTest() {
        //given
        val createChildTagRequest: CreateTag.Request = generateFixture()
        val createChildTagResponse: CreateTag.Response = generateFixture()

        val parentTagId = generateFixture<UUID>()

        given(tagController.createTag(parentTagId, createChildTagRequest)).willReturn(createChildTagResponse)

        val request = RestDocumentationRequestBuilders.post(TagApi.CREATE_TAG, parentTagId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(createChildTagRequest))

        //when
        val result = mockMvc.perform(request)

        //then
        result.andExpect(status().isOk)
            .andDo(resultHandler.document(
                pathParameters(
                    parameterWithName("parentTagId").description("상위 태그 id (생략 시 상위 태그 생성 API 호출)")
                ),
                requestFields(
                    fieldWithPath("name").description("태그 이름")
                ),
                responseFields(
                    fieldWithPath("id").description("생성된 태그 id")
                )
            )
        )
    }
}
