package com.bamyanggang.commonmodule.fixture

import com.navercorp.fixturemonkey.ArbitraryBuilder
import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder

// 특정 속성을 가진 임의 객체 생성
inline fun <reified T> generateFixture(propertyBuilder: (ArbitraryBuilder<T>) -> ArbitraryBuilder<T>): T {
    return FixtureMonkey.builder()
        .defaultNotNull(true)
        .plugin(KotlinPlugin())
        .build()
        .giveMeBuilder<T>()
        .apply { propertyBuilder(this) }
        .sample()
}

// 특정 속성 없는 임의 객체 생성
inline fun <reified T> generateFixture(): T {
    return generateFixture { it }
}

// 기본 타입의 임의의 객체
inline fun <reified T> generateBasicTypeFixture(length: Int): T {
    return FixtureMonkey.builder()
        .defaultNotNull(true)
        .plugin(KotlinPlugin())
        .build()
        .giveMeBuilder<T>()
        .sample()
}
