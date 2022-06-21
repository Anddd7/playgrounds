import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val coroutineVersion :String by project

plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    constraints {
        implementation(kotlin("stdlib-jdk8"))
    }

    implementation(platform(kotlin("bom")))
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test-junit"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutineVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion")

    testImplementation("io.mockk:mockk:1.12.3")
    testImplementation("org.assertj:assertj-core:3.22.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
