package com.bamyanggang.domainmodule.domain.strongpoint.exception

enum class StrongPointExceptionMessage(val message: String) {
    NOT_FOUND_STRONG_POINT("존재하지 않는 역량 키워드 입니다."),
    NAME_NOT_EMPTY("역량 키워드 이름은 공백일 수 없습니다."),
    DUPLICATED_NAME("역량 키워드 이름이 중복됩니다."),
}
