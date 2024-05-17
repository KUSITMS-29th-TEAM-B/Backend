plugins {
    `java-test-fixtures`

    id("org.asciidoctor.jvm.convert") version "4.0.2"

}

val asciidoctorExt = "asciidoctorExt"
configurations.create(asciidoctorExt) {
    extendsFrom(configurations.testImplementation.get())
}

dependencies {
    // security
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")

    //OAuth
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

    //RestDocs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testFixturesImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")

    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation(project(":Domain-Module"))
    implementation(project(":Common-Module"))
    implementation(project(":Infrastructure-Module:client"))
    implementation(project(":Infrastructure-Module:persistence"))
    implementation(project(":Support-Module:Jwt"))
    testImplementation(testFixtures(project(":Common-Module")))
    implementation(project(":Support-Module:cache"))
    implementation(project(":Support-Module:lock"))
}


val snippetDir = file("build/generated-snippets")

tasks.test {
    outputs.dir(snippetDir)
    useJUnitPlatform()
}

tasks.asciidoctor {
    inputs.dir(snippetDir)
    dependsOn(tasks.test)
    configurations(asciidoctorExt)
    baseDirFollowsSourceFile()
}
tasks.bootJar{
    dependsOn(tasks.asciidoctor)
    from("build/docs/asciidoc"){
        into("static/docs")
    }
}

tasks.register("copyDocs", Copy::class){
    dependsOn(tasks.bootJar)
    from("build/docs/asciidoc")
    into("src/main/resources/static/docs")
}

tasks.build {
    dependsOn(tasks.getByName("copyDocs"))
}
