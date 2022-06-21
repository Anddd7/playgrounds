
plugins {
    id("org.jetbrains.kotlin.jvm") // <1>
}

repositories {
    mavenCentral() // <2>
}

dependencies {
    constraints {
        implementation("org.apache.commons:commons-text:1.9") // <3>

        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }

    implementation(platform("org.jetbrains.kotlin:kotlin-bom")) // <4>

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") // <5>

    implementation(platform("org.jetbrains.kotlin:kotlin-bom")) // <6>

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1") // <7>
}

tasks.named<Test>("test") {
    useJUnitPlatform() // <8>
}
