plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.stradsw"
version = "1.0.0-SNAPSHOT"

description = "Rules API"

dependencies {
    implementation(project(":service"))
    implementation(project(":repository"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // For JSON serialization
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions") // Reactive Kotlin extensions
    implementation("org.jetbrains.kotlin:kotlin-reflect") // Required for Spring
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor") // For coroutines support

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
}

tasks.named<Test>("test") {
  useJUnitPlatform()
  maxHeapSize = "1G"
  testLogging {
    events("passed")
  }
}
