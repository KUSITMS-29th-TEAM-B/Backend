package com.bamyanggang.apimodule.domain.jobDescription.application

/**
 * Todo: securityContext를 어떻게 처리하지?
 */
//class JobDescriptionCreateServiceTest : BehaviorSpec({
//    val mockJobDescriptionAppender = mockk<JobDescriptionAppender>(relaxed = true)
//    val service = JobDescriptionCreateService(mockJobDescriptionAppender)
//    mockkStatic(SecurityContextHolder::class)
//    val securityContext = mockk<SecurityContext>()
//    val authentication = mockk<Authentication>()
//
//    given("JobDescriptionCreateService.createJobDescription") {
//        val request: CreateJobDescription.Request = generateFixture {
//            it.set("enterpriseName", "기업 이름")
//            it.set("title", "직무 공고 제목")
//            it.set("content", "직무 공고 내용")
//            it.set("link", "직무 공고 링크")
//            it.set("startedAt", LocalDateTime.now())
//            it.set("endedAt", LocalDateTime.now())
//        }
//        val userId : UUID = generateFixture()
//        every { SecurityContextHolder.getContext() } returns securityContext
//        every { securityContext.authentication } returns authentication
//        every { authentication.principal } returns userId
//
//        `when`("request가 주어지면") {
//            service.createJobDescription(request)
//
//            then("appendJobDescription이 호출된다.") {
//                verify {
//                    mockJobDescriptionAppender.appendJobDescription(
//                        enterpriseName = request.enterpriseName,
//                        title = request.title,
//                        content = request.content,
//                        link = request.link,
//                        startedAt = request.startedAt,
//                        endedAt = request.endedAt,
//                        userId = userId
//                    )
//                }
//            }
//        }
//    }
//})
