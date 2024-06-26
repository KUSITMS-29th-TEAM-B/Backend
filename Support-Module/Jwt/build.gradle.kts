dependencies {
    // configuration properties
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.12.5")
    //web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(project(":Common-Module"))
}
