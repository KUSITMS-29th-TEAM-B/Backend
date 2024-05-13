package com.bamyanggang.apimodule

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
import org.springframework.restdocs.operation.preprocess.Preprocessors

@TestConfiguration
class RestDocsConfig {

    @Bean
    fun restDocumentationResultHandler(): RestDocumentationResultHandler = MockMvcRestDocumentation.document(
        "{ClassName}/{methodName}",
        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
    )
}