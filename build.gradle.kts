plugins {
    id("application")
    id("checkstyle")
    id("com.github.ben-manes.versions") version "0.52.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

application { mainClass.set("org.example.Main") }

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate:hibernate-core:7.1.1.Final")

    implementation("org.postgresql:postgresql:42.7.8")

    implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}