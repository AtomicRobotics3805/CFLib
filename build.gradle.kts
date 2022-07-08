plugins {
    kotlin("jvm") version "1.7.10"
    `maven-publish`
}

val CFLibVersion = "0.1-SNAPSHOT"

group "com.atomicrobotics.cflib"
version "0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io/")
    maven(url = "https://maven.brott.dev/")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    api("com.acmerobotics.roadrunner:core:0.5.5")
}

sourceSets.main {
    java.srcDirs("core/src/main/kotlin")
}