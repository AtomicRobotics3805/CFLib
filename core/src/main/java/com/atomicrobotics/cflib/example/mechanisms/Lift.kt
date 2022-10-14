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
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.atomicrobotics.cflib.Constants.opMode
import com.atomicrobotics.cflib.Command
import com.atomicrobotics.cflib.subsystems.PowerMotor
import com.atomicrobotics.cflib.subsystems.Subsystem
import com.atomicrobotics.cflib.subsystems.MotorToPosition

/**
 * This class is an example of a lift controlled by a single motor. Unlike the Intake example object, it can use
 * encoders to go to a set position. Its first two commands, toLow and toHigh, do just that. The start command turns
 * the motor on and lets it spin freely, and the reverse command does the same but in the opposite direction. The stop
 * command stops the motor. These last three are meant for use during the TeleOp period to control the lift manually.
 * To use this class, copy it into the proper package and change the first eight constants (COUNTS_PER_INCH is fine as
 * is).
 */
@Config
@Suppress("Unused", "MemberVisibilityCanBePrivate")
object Lift : Subsystem {

    // configurable constants
    @JvmField
    var NAME = "lift"
    @JvmField
    var SPEED = 1.0
    @JvmField
    var HIGH_POSITION = 10.0 // in
    @JvmField
    var LOW_POSITION = 5.0 // in
    @JvmField
    var DIRECTION = DcMotorSimple.Direction.FORWARD

    // unconfigurable constants
    private const val PULLEY_WIDTH = 1.0 // in
    private const val COUNTS_PER_REV = 28 * 19.2 // NeveRest 20 orbital (really 19.2 ratio, not 20)
    private const val DRIVE_GEAR_REDUCTION = 1.0 // higher value means that driven gear is slower
    private const val COUNTS_PER_INCH = COUNTS_PER_REV * DRIVE_GEAR_REDUCTION / (PULLEY_WIDTH * Math.PI)

    // commands
    val toLow: Command
        get() = MotorToPosition(liftMotor, (LOW_POSITION * COUNTS_PER_INCH).toInt(), SPEED, listOf(this))
    val toHigh: Command
        get() = MotorToPosition(liftMotor, (HIGH_POSITION * COUNTS_PER_INCH).toInt(), SPEED, listOf(this))
    val start: Command
        get() = PowerMotor(liftMotor, SPEED, requirements = listOf(this))
    val reverse: Command
        get() = PowerMotor(liftMotor, -SPEED, requirements = listOf(this))
    val stop: Command
        get() = PowerMotor(liftMotor, 0.0, requirements = listOf(this))

    // motor
    lateinit var liftMotor: DcMotorEx

    /**
     * Initializes the liftMotor, resets its encoders, sets the mode to RUN_USING_ENCODER, and sets the direction to the
     * DIRECTION variable.
     */
    override fun initialize() {
        liftMotor = opMode.hardwareMap.get(DcMotorEx::class.java, NAME)
        liftMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        liftMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        liftMotor.direction = DIRECTION
    }
}