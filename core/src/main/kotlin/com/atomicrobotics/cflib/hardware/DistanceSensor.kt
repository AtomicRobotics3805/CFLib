@file:Suppress("unused")

package com.atomicrobotics.cflib.hardware

import com.qualcomm.robotcore.hardware.AnalogInput
import com.atomicrobotics.cflib.Constants

abstract class DistanceSensor(val name: String) {

    abstract val distance: Double
    abstract val minDistance: Double
    abstract val maxDistance: Double

    protected val input: AnalogInput =
        Constants.opMode.hardwareMap.get(AnalogInput::class.java, name)
    open val voltage: Double = input.voltage
}

class MROptical(name: String): DistanceSensor(name) {
    override val distance: Double
        get() = TODO("Not yet implemented")
    override val minDistance = 0.19685
    override val maxDistance = 3.14961
}

class REVOptical(name: String): DistanceSensor(name) {
    override val distance: Double
        get() = TODO("Not yet implemented")
    override val minDistance = 0.39370
    override val maxDistance = 3.93701
}

class REV2mDistance(name: String): DistanceSensor(name) {
    override val distance: Double
        get() = TODO("Not yet implemented")
    override val minDistance = 1.9685 // min distance is less than this
    override val maxDistance = 49.2126
}

class MBUltrasonic(name: String): DistanceSensor(name) {
    override val distance: Double
        get() = TODO("Not yet implemented")
    override val minDistance = 7.87402
    override val maxDistance = 301.181
}

class PSOptical2_15(name: String): DistanceSensor(name) {
    override val distance: Double
        get() = TODO("Not yet implemented")
    override val minDistance = 0.7874
    override val maxDistance = 5.90551
}

class PSOptical4_30(name: String): DistanceSensor(name) {
    override val distance: Double
        get() = TODO("Not yet implemented")
    override val minDistance = 1.5748
    override val maxDistance = 11.811
}

class PSOptical10_80(name: String): DistanceSensor(name) {
    override val distance: Double
        get() = TODO("Not yet implemented")
    override val minDistance = 3.93701
    override val maxDistance = 31.4961
}
