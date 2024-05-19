package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.enums.SortType
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import org.springframework.stereotype.Service
import java.util.*

@Service
class JodDescriptionReaderTest: BehaviorSpec({
    val jobDescriptionRepository: JobDescriptionRepository = mockk<JobDescriptionRepository>(relaxed = true)
    val jobDescriptionReader = JobDescriptionReader(jobDescriptionRepository)

    Given("userId가 주어졌을 때") {
        val userId : UUID = generateFixture()

        When("JobDescriptionReader.readJobDescriptionByUserIdAndSortType 함수가 호출되면") {
            jobDescriptionReader.readJobDescriptionByUserIdAndSortType(userId, 1, 10, SortType.CREATED, WriteStatus.WRITING)

            Then("jobDescriptionRepository.findAllByUserIdAndSortByCreatedAt 함수가 호출된다.") {
                verify { jobDescriptionRepository.findAllByUserIdAndSortByCreatedAt(userId, 1, 10, WriteStatus.WRITING) }
            }
        }
    }

    Given("jobDescriptionId가 주어졌을 때") {
        val jobDescriptionId : UUID = generateFixture()

        When("JobDescriptionReader.readJobDescriptionById 함수가 호출되면") {
            jobDescriptionReader.readJobDescriptionById(jobDescriptionId)

            Then("jobDescriptionRepository.findById 함수가 호출된다.") {
                verify { jobDescriptionRepository.findById(jobDescriptionId) }
            }
        }
    }

})
