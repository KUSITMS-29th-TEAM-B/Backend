plugins {
    `java-test-fixtures`

    id("org.asciidoctor.jvm.convert") version "4.0.2"

}

val asciidoctorExt = "asciidoctorExt"
configurations.create(asciidoctorExt) {
    extendsFrom(configurations.testImplementation.get())
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")

    //OAuth
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

    //RestDocs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testFixturesImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")

    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.security:spring-security-test")

    implementation(project(":Domain-Module"))
    implementation(project(":Common-Module"))
    implementation(project(":Infrastructure-Module"))
    implementation(project(":Support-Module"))
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

tasks.register<Copy>("copyDocument") {
    dependsOn(tasks.asciidoctor)
    val docsDir = file("src/main/resources/static/docs")
    val fromDir = file("build/docs/asciidoc")
    doFirst {
        if (docsDir.exists())
            delete(docsDir)
    }
    from(fromDir)
    into(docsDir)

}

tasks.build {
    dependsOn(tasks.getByName("copyDocument"))
}