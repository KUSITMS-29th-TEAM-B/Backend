dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

    // Feign
    implementation("io.github.openfeign:feign-httpclient:12.1")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.3")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.12.5")

    //OAuth
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
}
