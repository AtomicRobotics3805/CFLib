/*
    Copyright (c) 2022 Atomic Robotics (https://atomicrobotics3805.org)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.atomicrobotics.cflib.example.mechanisms

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.AnalogInput
import com.atomicrobotics.cflib.Constants
import com.atomicrobotics.cflib.subsystems.Subsystem

/**
 * This subsystem uses a distance sensor to determine if the robot is properly aligned at startup.
 * It doesn't have any actual commands, but the alignedProperly value can be used by other
 * subsystems such as LED. The targetDistance variable is the distance range that the sensor should
 * be picking up under proper circumstances in inches.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
@Config
object DistanceSensorAlignment : Subsystem {

    @JvmField
    var NAME = "distanceSensor"
    @JvmField
    var TARGET_DISTANCE = Pair(10.0, 50.0) // in

    val alignedProperly: Boolean
        get() = voltageToDistance(distanceSensor.voltage) in
                TARGET_DISTANCE.first..TARGET_DISTANCE.second

    private lateinit var distanceSensor: AnalogInput

    /**
     * Initializes the distanceSensor.
     */
    override fun initialize() {
        distanceSensor = Constants.opMode.hardwareMap.get(AnalogInput::class.java, NAME)
    }

    private fun voltageToDistance(voltage: Double) = voltage * 30.0
}