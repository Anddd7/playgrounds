rootProject.name = "playgrounds-kotlin"

pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
    }
}

include(":leetcode-kotlin")
include(":model-pattern-objects")
