plugins {
    kotlin("jvm") version "1.7.10"
    `maven-publish`
}

val CFLibVersion = "0.0.1"

group "com.atomicrobotics.cflib"
version "0.0.1"

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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.atomicrobotics.cflib"
            artifactId = "cflib"
            version = CFLibVersion

            from(components["java"])
        }
    }
}