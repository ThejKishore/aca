plugins {
    java
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.bmuschko.docker-spring-boot-application") version "9.4.0"
}

group = "com.kish.learn"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
//    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("io.dapr:dapr-sdk:1.10.0")
    // Dapr"s SDK for Actors (optional).
    implementation("io.dapr:dapr-sdk-actors:1.10.0")
    // Dapr"s SDK integration with SpringBoot (optional).
    implementation("io.dapr:dapr-sdk-springboot:1.10.0")

    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}


docker {
    springBootApplication {
        baseImage.set("mcr.microsoft.com/java/jdk:17-zulu-alpine")
        ports.set(listOf(7101, 7101))
        images.set(setOf("thej1/employee-svc:0.0.1", "thej1/employee-svc:latest"))
//        jvmArgs.set(listOf("-Dspring.profiles.active=production", "-Xmx2048m"))
    }
}