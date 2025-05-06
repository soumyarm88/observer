import org.gradle.kotlin.dsl.testImplementation
import org.gradle.kotlin.dsl.testRuntimeOnly

plugins {
    `java-library`
    id("io.freefair.aspectj") version "8.13.1"
    id("org.assertj.generator") version "1.1.1" apply false
    id("maven-publish")
}

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

    implementation("org.aspectj:aspectjrt:1.9.24")
    implementation("org.aspectj:aspectjweaver:1.9.24")

    implementation("org.slf4j:slf4j-api:2.0.16")
    testImplementation("org.slf4j:slf4j-simple:2.0.17")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.assertj:assertj-core:3.27.3")
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "tech.soumyarm88.lib"
            artifactId = "observer"
            version = "1.0.2"

            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/soumyarm88/observer")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
}
