import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-test-fixtures")
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"

    kotlin("jvm") version "1.9.23"
    kotlin("kapt") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
}

val kotestVersion = "5.8.1"
val mockkVersion = "1.13.10"

allprojects {
    group = "com.bamyanggang"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.withType<BootJar> {
        enabled=false
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "java-test-fixtures")



    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    kapt {
        keepJavacAnnotationProcessors = true
    }

    dependencies {
        // kotlin
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        // fixture testing tool
        testImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:1.0.14")
        testFixturesImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:1.0.14")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")

        // mvc
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testFixturesImplementation("org.springframework.boot:spring-boot-starter-test")

        // mockk
        testImplementation("io.mockk:mockk:${mockkVersion}")

        //kotest
        testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")  // Test Framework
        testImplementation("io.kotest:kotest-assertions-core:$kotestVersion") // Assertions Library
        testImplementation("io.kotest:kotest-property:$kotestVersion") // Property Testing
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}

project(":Api-Module"){
    apply(plugin = "org.springframework.boot")

    tasks.withType<BootJar> {
        enabled=true
    }
}
