import org.gradle.kotlin.dsl.testImplementation
import org.gradle.kotlin.dsl.testRuntimeOnly

plugins {
    `java-library`
    id("io.freefair.aspectj") version "8.13.1"
    id("org.assertj.generator") version "1.1.1" apply false
    id("maven-publish")
}

group = "tech.soumyarm88.lib"
version = "0.1.0"

tasks.jar {
    manifest {
        attributes(mapOf(
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    api(libs.commons.math3)
    implementation(libs.guava)

    implementation("org.aspectj:aspectjrt:1.9.+")
    implementation("org.aspectj:aspectjweaver:1.9.+")

    implementation("org.slf4j:slf4j-api:2.0.+")
    testImplementation("org.slf4j:slf4j-simple:2.0.+")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.assertj:assertj-core:3.24.+")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    withSourcesJar()
    withJavadocJar()
}

tasks.test {
    useJUnitPlatform()
}

subprojects {
    apply(plugin = "maven-publish")
    configure<PublishingExtension> {
        repositories {
            maven {
                name = "ObserverLibrary"
                url = uri("https://maven.pkg.github.com/soumyarm88/observer")
                credentials {
                    username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                    password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
                }
            }
        }
        publications {
            register<MavenPublication>("gpr") {
                from(components["java"])
            }
        }
    }
}