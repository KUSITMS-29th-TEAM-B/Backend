package com.bamyanggang.apimodule.domain.jobDescription.scheduler

import com.bamyanggang.apimodule.domain.jobDescription.application.service.JobDescriptionInfoUpdateService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class UpdateWriteStatusScheduler(
    private val jobDescriptionInfoUpdateService: JobDescriptionInfoUpdateService
) {

    // 1분마다 돌아감
    @Scheduled(cron = "0 * * * * *", zone = "Asia/Seoul")
    fun changeJobDescriptionWritingStatus() = jobDescriptionInfoUpdateService.invoke()

}
