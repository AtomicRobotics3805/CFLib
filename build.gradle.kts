import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    "java"
    `java-library`
    kotlin("jvm") version "1.6.21"
    `maven-publish`
}

group = "com.atomicrobotics.cflib"
version = "0.0.1"

repositories {
    mavenCentral()
    google()
    jcenter()
}

dependencies {
    testImplementation(kotlin("test"))
    //classpath("com.android.tools.build:gradle:3.1.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.atomicrobotics.cflib"
            artifactId = "library"
            version = "0.0.1"

            from(components["java"])
        }
    }
}