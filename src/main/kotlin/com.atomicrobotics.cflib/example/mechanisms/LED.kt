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
import com.qualcomm.robotcore.hardware.DigitalChannel
import com.atomicrobotics.cflib.Command
import com.atomicrobotics.cflib.Constants
import com.atomicrobotics.cflib.subsystems.Subsystem

/**
 * This is an example of how someone might use an LED to communicate information with the drivers.
 * It uses the DistanceSensorAlignment subsystem to determine whether or not the robot is aligned
 * properly, and then turns on the LED if it's not. The distance sensor and LED are two different
 * mechanisms because, theoretically, they could be used separately for other tasks.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
@Config
object LED : Subsystem {

    @JvmField
    var NAME = "LED"

    private lateinit var led: DigitalChannel

    /**
     * Initializes the led.
     */
    override fun initialize() {
        led = Constants.opMode.hardwareMap.get(DigitalChannel::class.java, NAME)
    }

    /**
     * Checks with DistanceSensorAlignment to make sure that the robot is properly aligned, and if
     * it's not, turns on the LED.
     */
    class VerifyAlignment: Command() {
        override val _isDone: Boolean
            get() = Constants.opMode.isStarted
        override val interruptible = true
        override val requirements = listOf(LED)

        /**
         * Sets LED state to the inverse of whether the robot is aligned properly
         */
        override fun execute() {
            led.state = !DistanceSensorAlignment.alignedProperly
        }
    }
}