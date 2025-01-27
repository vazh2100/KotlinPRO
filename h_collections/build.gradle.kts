import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    kotlin("jvm") version "2.0.21"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
}

group = "com.vazh2100"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

extensions.configure<DetektExtension> {
    buildUponDefaultConfig = true
    parallel = true
    allRules = false
    config.setFrom("$rootDir/config/detekt.yaml")
}

tasks.withType<Detekt> {
    jvmTarget = "1.8"
    reports {
        html.required.set(true)
    }
}

tasks.withType<DetektCreateBaselineTask> {
    jvmTarget = "1.8"
}
