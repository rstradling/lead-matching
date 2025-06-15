plugins {
    // Apply Java plugin (Spring Boot not needed unless executable JAR is required)
    java
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.stradsw"
version = "1.0.0-SNAPSHOT"

description = "Rules Service"

// Inherit repositories and Java settings from root build.gradle.kts
// Repositories (mavenCentral) and Java 21 are already configured in root

dependencies {
    // Internal module dependency
    implementation(project(":repository"))

    // Spring Boot core dependency
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
}

// Configure Java compilation (inherits Java 21 from root, but explicitly set for clarity)
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

