plugins {
    kotlin("jvm") version "1.3.70"
    antlr
}

group = "ru.art2000"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    antlr("org.antlr:antlr4:4.8")

    implementation(fileTree("lib"))

    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.3.70")
    implementation(kotlin("reflect"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}