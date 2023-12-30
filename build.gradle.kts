plugins {
    kotlin("jvm") version "1.9.0"
    application
    id("org.graalvm.buildtools.native") version "0.9.28"
}

group = "de.gmasil"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass = "de.gmasil.example.AppKt"
}

tasks.jar {
    manifest.attributes["Main-Class"] = "de.gmasil.example.AppKt"
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

graalvmNative {
    binaries.all {
        resources.autodetect()
    }
    toolchainDetection = false
}
