[![](https://jitpack.io/v/AtomicRobotics3805/CFLib.svg)](https://jitpack.io/#AtomicRobotics3805/CFLib)
# CFLib
### Command-based FTC programming library
This project contains a complex system to schedule and run various commands, which are used to perform complex tasks easily. 

It also integrates [RoadRunner](https://github.com/acmerobotics/road-runner) and [MeepMeep](https://github.com/NoahBres/MeepMeep)
to maximize programming efficiency.

## Installation in 3 easy steps 
### Groovy
1) Add the maven repository to the end of your root `build.gradle`
```groovy
repositories {
    mavenCentral()
//  ...
    maven { url = 'https://jitpack.io/' }
}
```
2) Add the dependency to `TeamCode/build.gradle`
```groovy
dependencies {
//  ...
    implementation 'com.github.AtomicRobotics3805:CFLib:0.0.1'
}
```
3) Sync gradle
### Kotlin
1) Add the maven repository to the end of your root `build.gradle.kts`
```kotlin
repositories {
    mavenCentral()
//  ...
    maven("https://jitpack.io/")
}
```
2) Add the dependency to `TeamCode/build.gradle.kts`
```kotlin
dependencies {
//  ...
    implementation("com.github.AtomicRobotics3805:CFLib:0.0.1")
}
```
3) Sync gradle
