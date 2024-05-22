package com.bamyanggang.apimodule.domain.strongpoint.presentation

import com.bamyanggang.apimodule.domain.strongpoint.application.dto.CreateStrongPoint
import com.bamyanggang.apimodule.domain.strongpoint.application.dto.GetStrongPoint
import com.bamyanggang.apimodule.domain.strongpoint.application.service.StrongPointCreateService
import com.bamyanggang.apimodule.domain.strongpoint.application.service.StrongPointDeleteService
import com.bamyanggang.apimodule.domain.strongpoint.application.service.StrongPointGetService
import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.Keyword
import com.bamyanggang.persistence.strongpoint.jpa.repository.KeywordJpaRepository
import com.bamyanggang.persistence.strongpoint.mapper.KeywordMapper
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class StrongPointController(
    private val strongPointCreateService: StrongPointCreateService,
    private val strongPointDeleteService: StrongPointDeleteService,
    private val strongPointGetService: StrongPointGetService,
    private val keywordJpaRepository: KeywordJpaRepository,
    private val keywordMapper: KeywordMapper
) {
    @GetMapping(StrongPointApi.BASE_URL)
    fun getAllStrongPoints(): GetStrongPoint.Response {
        return strongPointGetService.getAllStrongPoints()
    }

    @PostMapping(StrongPointApi.BASE_URL)
    fun createStrongPoint(@RequestBody request: CreateStrongPoint.Request): CreateStrongPoint.Response{
        return strongPointCreateService.createStrongPoint(request)
    }
    
    @DeleteMapping(StrongPointApi.STRONG_POINT_PATH_VARIABLE_URL)
    fun deleteStrongPoint(@PathVariable("strongPointId") strongPointId: UUID) {
        strongPointDeleteService.deleteStrongPoint(strongPointId)
    }

    // 기본 역량 키워드 데이터 세팅 임시 API
    data class Data(
        val points : List<KeywordData>
    ) {

        data class KeywordData (
            val id: UUID,
            val name: String
        )
    }

    @PostMapping("/api/set-data")
    fun setData(@RequestBody data : Data) {
        val datas = data.points.map { Keyword(it.id, it.name) }
        val jpaDatas = datas.map { keywordMapper.toJpaEntity(it) }

        keywordJpaRepository.saveAll(jpaDatas)
    }

    @GetMapping("/api/default-points")
    fun getAllPoints() : List<Keyword> {
        return keywordJpaRepository.findAll().map { keywordMapper.toDomainEntity(it) }
    }
}
