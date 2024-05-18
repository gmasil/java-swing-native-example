plugins {
    id("java")
    id("application")
    id("org.graalvm.buildtools.native") version "0.9.28"
}

group = "de.gmasil"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
        vendor = JvmVendorSpec.GRAAL_VM
        implementation = JvmImplementation.VENDOR_SPECIFIC
    }
}

application {
    mainClass = "de.gmasil.example.App"
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
        buildArgs.add("-Djava.awt.headless=false")
        javaLauncher.set(javaToolchains.launcherFor {
            languageVersion.set(java.toolchain.languageVersion)
            vendor.set(java.toolchain.vendor)
            implementation.set(java.toolchain.implementation)
        })
    }
    toolchainDetection = true
    agent {
        defaultMode.set("standard")
        enabled.set(true)
        metadataCopy {
            inputTaskNames.add("run")
            outputDirectories.add("src/main/resources/META-INF/native-image")
            mergeWithExisting.set(true)
        }
    }
}

val cleanMetadata = task("cleanMetadata") {
    doLast {
        delete(fileTree("src/main/resources/META-INF/native-image"){
            include("*.json")
        })
    }
}

tasks.named("run") {
    mustRunAfter(cleanMetadata)
}

tasks.named("metadataCopy") {
    mustRunAfter("run")
}

task("updateMetadata") {
    dependsOn(cleanMetadata, "assemble", "run", "metadataCopy")
}
