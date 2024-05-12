package com.bamyanggang.supportmodule.uuid

import java.util.*
import com.github.f4b6a3.uuid.UuidCreator

object UuidCreator {

    /**
     * 시간 순서대로 고유한 식별자를 반환
     * 시간과 단조 랜덤 두 부분으로 식별자를 생성
     * 시간이 반복될 때 단조 랜덤 비트는 1부터 2^32 사이의 임의의 숫자로 증가
     */
    fun create(): UUID {
        return UuidCreator.getTimeOrderedEpochPlus1()
    }

}
