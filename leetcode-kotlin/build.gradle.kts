import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val junit5ApiVersion = "5.5.1"
val assertjVersion = "3.13.2"

plugins {
    kotlin("jvm") version "1.3.50"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))

    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit5ApiVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junit5ApiVersion")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit5ApiVersion")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}
