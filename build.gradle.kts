import com.adarshr.gradle.testlogger.theme.ThemeType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val modelMapperVersion = "3.1.1"
val jwtApiVersion = "0.11.5"
val fakerVersion = "1.0.2"
val jUnitVersion = "5.9.3"
val mockitoKotlin = "4.1.0"
val flyway = "9.17.0"
val testContainers = "1.17.6"

plugins {
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.allopen") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22"
    id("com.adarshr.test-logger") version "3.2.0"
}

group = "com.higor"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

dependencies {
    /* Spring */
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-cache")

    /* Custom */
    implementation("org.flywaydb:flyway-core:$flyway")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.postgresql:postgresql")
    implementation("org.modelmapper:modelmapper:$modelMapperVersion")
    implementation("io.jsonwebtoken:jjwt-api:$jwtApiVersion")
    implementation("io.jsonwebtoken:jjwt-impl:$jwtApiVersion")
    implementation("io.jsonwebtoken:jjwt-jackson:$jwtApiVersion")

    /* Tests */
    implementation("com.github.javafaker:javafaker:1.0.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("junit", "junit")
        exclude("org.mockito", "mockito")
    }
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlin")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
    testImplementation("org.testcontainers:testcontainers:$testContainers")
    testImplementation("org.testcontainers:junit-jupiter:$testContainers")
    testImplementation("org.testcontainers:postgresql:$testContainers")
}

testlogger {
    theme = ThemeType.MOCHA_PARALLEL
    showExceptions = true
    showStackTraces = true
    showFullStackTraces = false
    showCauses = true
    slowThreshold = 2000
    showSummary = true
    showSimpleNames = false
    showPassed = true
    showSkipped = true
    showFailed = true
    showOnlySlow = false
    showStandardStreams = false
    showPassedStandardStreams = true
    showSkippedStandardStreams = true
    showFailedStandardStreams = true
    logLevel = LogLevel.LIFECYCLE
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
