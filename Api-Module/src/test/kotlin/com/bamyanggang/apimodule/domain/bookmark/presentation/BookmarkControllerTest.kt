package com.bamyanggang.apimodule.domain.bookmark.presentation

import com.bamyanggang.apimodule.BaseRestDocsTest
import com.bamyanggang.apimodule.domain.bookmark.application.BookmarkService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest(BookmarkController::class)
class BookmarkControllerTest: BaseRestDocsTest() {

    @MockBean
    private lateinit var bookmarkService: BookmarkService

    @Test
    @DisplayName("북마크 추가")
    fun createBookmark() {
        // given
        val jobDescriptionId = UUID.randomUUID()
        val experienceId = UUID.randomUUID()

        val request = RestDocumentationRequestBuilders.patch(BookmarkApi.BOOKMARK, jobDescriptionId, experienceId)
            .header("Authorization", "Bearer Access Token")
            .contentType(MediaType.APPLICATION_JSON_VALUE)

        //when
        val result = mockMvc.perform(request)

        // then
        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("엑세스 토큰")
                    ),
                    pathParameters(
                        parameterWithName("jobDescriptionId").description("jd 공고 ID"),
                        parameterWithName("experienceId").description("경험 ID")
                    )
                )
            )
    }


}
