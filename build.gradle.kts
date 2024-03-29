import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    kotlin("jvm") version "1.9.21"
}

group = "com.ytikhov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation(kotlin("stdlib-jdk8"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
}