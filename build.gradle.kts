plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
    implementation("org.seleniumhq.selenium:selenium-java:4.27.0")

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