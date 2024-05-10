plugins {
    kotlin("plugin.lombok") version "1.9.24"
    id("io.freefair.lombok") version "8.1.0"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")
    // webclient
    implementation("org.springframework.boot:spring-boot-starter-webflux")
}