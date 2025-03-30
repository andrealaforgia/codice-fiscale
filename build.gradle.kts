plugins {
    id("java")
    id("info.solidsoft.pitest") version "1.15.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.17.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.27.3")

    // Ensure PIT supports JUnit 5
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    // Add the PIT JUnit 5 plugin
    pitest("org.pitest:pitest-junit5-plugin:1.2.0")
}

pitest {
    junit5PluginVersion.set("1.2.0")
    reportDir.set(file("./target/pit-reports"))
    targetClasses.set(listOf("com.andrealaforgia.italianfiscalcode.*"))
    targetTests.set(listOf("com.andrealaforgia.italianfiscalcode.*"))
}

tasks.withType<JavaCompile>().configureEach {
    sourceCompatibility = "17"
    targetCompatibility = "17"
}

tasks.test {
    useJUnitPlatform()
}
