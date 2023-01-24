plugins {
    kotlin("jvm") apply true
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("com.h2database:h2:2.1.214")
}

tasks.test {
    useJUnitPlatform()
}
