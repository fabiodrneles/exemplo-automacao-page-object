plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

// 003 FR-1: toolchain Java 21 (decisão D2).
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.1.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.seleniumhq.selenium:selenium-java:4.49.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    // Repassa -DbaseUrl, -Dheadless e -Dchrome.binary para os testes (spec 001 FR-1, FR-2).
    listOf("baseUrl", "headless", "chrome.binary").forEach { key ->
        System.getProperty(key)?.let { systemProperty(key, it) }
    }
    testLogging {
        events ("passed", "skipped", "failed")
    }
}