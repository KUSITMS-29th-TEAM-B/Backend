dependencies {
    implementation(project(":Common-Module"))
    implementation(project(":Support-Module:uuid"))
    testImplementation(testFixtures(project(":Common-Module")))
}
