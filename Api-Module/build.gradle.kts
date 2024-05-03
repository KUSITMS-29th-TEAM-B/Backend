dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")

    // webclient
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    //OAuth
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

    implementation(project(":Domain-Module"))
    implementation(project(":Common-Module"))
}
