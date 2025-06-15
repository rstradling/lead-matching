plugins {
    java
    kotlin("jvm") version "2.0.0"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.stradsw"
version = "1.0.0-SNAPSHOT"

description = "Rules API"

dependencies {
    implementation(project(":service"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

tasks.named<Test>("test") {
  useJUnitPlatform()
  maxHeapSize = "1G"
  testLogging {
    events("passed")
  }
}
