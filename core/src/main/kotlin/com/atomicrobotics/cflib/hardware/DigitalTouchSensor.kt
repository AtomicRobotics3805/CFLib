package com.atomicrobotics.cflib.hardware

import com.qualcomm.robotcore.hardware.DigitalChannel
import com.atomicrobotics.cflib.Constants

class DigitalTouchSensor(name: String) {

    val pressed: Boolean
        get() = input.state
    private val input: DigitalChannel =
        Constants.opMode.hardwareMap.get(DigitalChannel::class.java, name)
}