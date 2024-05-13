package com.bamyanggang.apimodule

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.context.annotation.Import
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter
import java.nio.charset.StandardCharsets

@AutoConfigureRestDocs
@Import(RestDocsConfig::class, FilterConfig::class)
@ExtendWith(RestDocumentationExtension::class)
abstract class BaseRestDocsTest {
    @Autowired
    lateinit var resultHandler: RestDocumentationResultHandler

    @Autowired
    lateinit var objectMapper: ObjectMapper

    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun mockMvcSetup(
        applicationContext: WebApplicationContext,
        contextProvider: RestDocumentationContextProvider
    ){
        mockMvc= MockMvcBuilders.webAppContextSetup(applicationContext)
            .apply<DefaultMockMvcBuilder>(
                MockMvcRestDocumentation.documentationConfiguration(contextProvider).uris()
                    .withScheme("https")
            ).alwaysDo<DefaultMockMvcBuilder?>(resultHandler)
            .addFilters<DefaultMockMvcBuilder?>(CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
            .build()
    }
}