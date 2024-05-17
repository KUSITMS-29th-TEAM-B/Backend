package com.bamyanggang.apimodule.domain.user.presentation

import com.bamyanggang.apimodule.BaseRestDocsTest
import com.bamyanggang.apimodule.domain.user.application.dto.ProfileImage
import com.bamyanggang.apimodule.domain.user.application.dto.Register
import com.bamyanggang.apimodule.domain.user.application.dto.UserInfo
import com.bamyanggang.apimodule.domain.user.application.service.ProfileImageGetService
import com.bamyanggang.apimodule.domain.user.application.service.UserCreateService
import com.bamyanggang.apimodule.domain.user.application.service.UserInfoGetService
import com.bamyanggang.apimodule.domain.user.application.service.UserInfoUpdateService
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

@WebMvcTest(UserController::class)
class UserControllerTest : BaseRestDocsTest(){

        @MockBean
        private lateinit var userCreateService: UserCreateService
        @MockBean
        private lateinit var profileImageGetService: ProfileImageGetService
        @MockBean
        private lateinit var userInfoGetService: UserInfoGetService
        @MockBean
        private lateinit var userInfoUpdateService: UserInfoUpdateService

        @Test
        @DisplayName("회원가입을 진행한다.")
        fun register() {
            //given
            val registerRequest: Register.Request = generateFixture()
            val registerResponse: Register.Response.Success = generateFixture()
            given(userCreateService.createUser(registerRequest)).willReturn(registerResponse)
            val request = RestDocumentationRequestBuilders.post(UserApi.REGISTER)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(registerRequest))
            //when
            val result = mockMvc.perform(request)
            //then
            result.andExpect(status().isOk)
                .andDo(
                    resultHandler.document(
                        requestFields(
                            fieldWithPath("registrationToken").description("회원가입용 토큰"),
                            fieldWithPath("profileImgUrl").description("프로필 이미지 URL"),
                            fieldWithPath("provider").description("소셜 로그인 플랫폼(GOOGLE, KAKAO)"),
                            fieldWithPath("nickName").description("닉네임"),
                            fieldWithPath("jobSearchStatus").description("구직 상태"),
                            fieldWithPath("desiredJob").description("희망 직무"),
                            fieldWithPath("goal").description("목표"),
                            fieldWithPath("dream").description("꿈")
                        ),
                        responseFields(
                            fieldWithPath("accessToken").description("서버 접근을 위한 accessToken"),
                            fieldWithPath("refreshToken").description("서버 접근을 위한 refreshToken")
                        )
                    )
                )
        }

        @Test
        @DisplayName("기본 프로필 이미지 url을 가져온다.")
        fun getProfileImages() {
            //given
            val profileImageResponse: ProfileImage.Response = generateFixture()
            given(profileImageGetService.getProfileImages()).willReturn(profileImageResponse)
            val request = RestDocumentationRequestBuilders.get(UserApi.PROFILE_IMG)
            //when
            val result = mockMvc.perform(request)
            //then
            result.andExpect(status().isOk)
                .andDo(
                    resultHandler.document(
                        responseFields(
                            fieldWithPath("profileImgUrl").description("프로필 이미지 URL 리스트")
                        )
                    )
                )
        }

        @Test
        @DisplayName("사용자 정보를 가져온다.")
        fun getUserInfo() {
            //given
            val userInfoResponse: UserInfo.Response.Success = generateFixture()
            given(userInfoGetService.getUserInfo()).willReturn(userInfoResponse)
            val request = RestDocumentationRequestBuilders.get(UserApi.USER_INFO)
            //when
            val result = mockMvc.perform(request)
            //then
            result.andExpect(status().isOk)
                .andDo(
                    resultHandler.document(
                        responseFields(
                            fieldWithPath("nickName").description("닉네임"),
                            fieldWithPath("profileImgUrl").description("프로필 이미지 URL"),
                            fieldWithPath("jobSearchStatus").description("구직 상태"),
                            fieldWithPath("desiredJob").description("희망 직무"),
                            fieldWithPath("goal").description("목표"),
                            fieldWithPath("dream").description("꿈")
                        )
                    )
                )
        }

    @Test
    @DisplayName("사용자 정보를 수정한다.")
    fun updateUserInfo() {
        //given
        val userInfoUpdateRequest: UserInfo.Request.UpdateUserInfo = generateFixture()
        val request = RestDocumentationRequestBuilders.patch(UserApi.USER_INFO_UPDATE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(userInfoUpdateRequest))
        //when
        val result = mockMvc.perform(request)
        //then
        result.andExpect(status().isOk)
            .andDo(
                resultHandler.document(
                    requestFields(
                        fieldWithPath("nickName").description("닉네임"),
                        fieldWithPath("profileImgUrl").description("프로필 이미지 URL"),
                        fieldWithPath("jobSearchStatus").description("구직 상태"),
                        fieldWithPath("desiredJob").description("희망 직무"),
                        fieldWithPath("goal").description("목표"),
                        fieldWithPath("dream").description("꿈")
                    )
            )
        )
    }
}
