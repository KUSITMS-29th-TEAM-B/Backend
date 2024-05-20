package com.bamyanggang.apimodule.domain.user.presentation

import com.bamyanggang.apimodule.BaseRestDocsTest
import com.bamyanggang.apimodule.domain.user.application.dto.Reissue
import com.bamyanggang.apimodule.domain.user.application.dto.SocialLogin
import com.bamyanggang.apimodule.domain.user.application.service.AuthService
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import org.junit.jupiter.api.DisplayName
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import com.bamyanggang.commonmodule.fixture.generateFixture
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders

@WebMvcTest(AuthController::class)
class AuthControllerTest : BaseRestDocsTest(){

    @MockBean
    private lateinit var authService: AuthService


    @Test
    @DisplayName("소셜 로그인시 사용자가 가입되어 있으면 accessToken, refreshToken을 반환한다.")
    fun socialLoginWithRegisterUser() {
        //given
        val provider : SocialLoginProvider = generateFixture()
        val requestAccessToken : String = generateFixture()
        val socialLoginRequest = SocialLogin.Request(requestAccessToken)
        val responseAccessToken : String = generateFixture()
        val responseRefreshToken : String = generateFixture()
        val socialLoginResponse = SocialLogin.Response.Success(responseAccessToken, responseRefreshToken)
        val request = RestDocumentationRequestBuilders.post(AuthApi.SOCIAL_LOGIN, provider)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(socialLoginRequest))
        given(authService.executeSocialLogin(provider, socialLoginRequest)).willReturn(socialLoginResponse)
        //when
        val result = mockMvc.perform(request)
        //then
        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestFields(
                        fieldWithPath("accessToken").description("소셜 로그인 플랫폼에서 발급받은 accessToken"),
                    ),
                    pathParameters(
                        parameterWithName("provider").description("소셜 로그인 플랫폼(GOOGLE, KAKAO)")
                    ),
                    responseFields(
                        fieldWithPath("accessToken").description("서버 접근을 위한 accessToken"),
                        fieldWithPath("refreshToken").description("서버 접근을 위한 refreshToken")
                    )
                )
            )
    }

    @Test
    @DisplayName("소셜 로그인시 사용자가 가입되어 있지 않으면 registrationToken, 소셜 로그인 닉네임을 반환한다.")
    fun socialLoginWithUnRegisterUser() {
        //given
        val provider : SocialLoginProvider = generateFixture()
        val requestAccessToken : String = generateFixture()
        val socialLoginRequest = SocialLogin.Request(requestAccessToken)
        val responseRegistrationToken : String = generateFixture()
        val responseNickName : String = generateFixture()
        val socialLoginResponse = SocialLogin.Response.UnRegistered(responseRegistrationToken, responseNickName)
        val request = RestDocumentationRequestBuilders.post(AuthApi.SOCIAL_LOGIN, provider)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(socialLoginRequest))
        given(authService.executeSocialLogin(provider, socialLoginRequest)).willReturn(socialLoginResponse)
        //when
        val result = mockMvc.perform(request)
        //then
        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestFields(
                        fieldWithPath("accessToken").description("소셜 로그인 플랫폼에서 발급받은 accessToken"),
                    ),
                    pathParameters(
                        parameterWithName("provider").description("소셜 로그인 플랫폼(GOOGLE, KAKAO)")
                    ),
                    responseFields(
                        fieldWithPath("registrationToken").description("서버에 가입되어 있지 않은 사용자를 위한 registrationToken"),
                        fieldWithPath("nickName").description("소셜 로그인 플랫폼에서 받은 닉네임")
                    )
                )
            )
    }

    @Test
    @DisplayName("토큰 재발급시 accessToken, refreshToken을 반환한다.")
    fun reissueToken() {
        //given
        val reissueRequest : Reissue.Request = generateFixture()
        val reissueResponse : Reissue.Response = generateFixture()
        given(authService.reissueToken(reissueRequest)).willReturn(reissueResponse)
        val request = RestDocumentationRequestBuilders.put(AuthApi.REISSUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(reissueRequest))
        //when
        val result = mockMvc.perform(request)
        //then
        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestFields(
                        fieldWithPath("refreshToken").description("재발급을 위한 refreshToken"),
                    ),
                    responseFields(
                        fieldWithPath("accessToken").description("재발급된 accessToken"),
                        fieldWithPath("refreshToken").description("재발급된 refreshToken")
                    )
                )
            )
    }

    @Test
    @DisplayName("로그아웃 요청시 성공한다.")
    fun logout() {
        //given
        val request = RestDocumentationRequestBuilders.delete(AuthApi.LOGOUT)
            .header("Authorization","Bearer accessToken")
            .header("RefreshToken","Bearer refreshToken")
            .contentType(MediaType.APPLICATION_JSON_VALUE)

        //when
        val result = mockMvc.perform(request)
        //then
        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("Access Token"),
                        headerWithName("RefreshToken").description("refreshToken")
                    )
                )
            )
    }

}
