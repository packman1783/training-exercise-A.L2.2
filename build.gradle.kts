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
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}