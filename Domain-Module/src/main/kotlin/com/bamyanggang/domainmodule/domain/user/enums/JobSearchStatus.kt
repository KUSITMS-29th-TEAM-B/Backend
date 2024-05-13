package com.bamyanggang.domainmodule.domain.user.enums

enum class JobSearchStatus(val description: String) {
    NOT_READY("아직 취업을 본격적으로 준비하지 않는다."),
    PASSIVE("당장 직무공고를 찾아보지는 않지만, 대외활동이나 학회, 인턴 등 취업을 위한 준비 활동을 하고 있다."),
    ACTIVE("직무공고를 탐색하고, 취업을 위한 적극적인 구직 활동을 하고 있다."),
}