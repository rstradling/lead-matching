plugins {
    java
    kotlin("jvm") version "2.0.0" apply false // Apply false to avoid applying to root
}

group = "com.stradsw"
version = "1.0.0-SNAPSHOT"

// Define properties
extra["javaVersion"] = "21"
extra["springBootVersion"] = "3.3.4"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    dependencies {
        // Use rootProject.extra to access springBootVersion
        implementation(platform("org.springframework.boot:spring-boot-dependencies:${rootProject.extra["springBootVersion"]}"))
        implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")
    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.add("--parameters")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "21"
        }
    }
}
