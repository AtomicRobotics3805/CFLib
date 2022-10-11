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
package com.atomicrobotics.cflib.driving

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.hardware.Gamepad
import com.atomicrobotics.cflib.Command
import com.atomicrobotics.cflib.Constants
import com.atomicrobotics.cflib.Constants.color
import com.atomicrobotics.cflib.Constants.drive
import com.atomicrobotics.cflib.subsystems.Subsystem
import com.atomicrobotics.cflib.trajectories.toRadians
import kotlin.math.*

/**
 * Controls the robot manually using a gamepad. Left stick up/down moves the robot forwards/backwards, left stick left/
 * right moves the robot left/right, right stick left/right makes the robot turn left/right
 *
 * @param gamepad the gamepad that controls the driving
 * @param requirements any Subsystems used by this command
 * @param interruptible whether this command can be interrupted or not
 * @param reverseStrafe whether to reverse the left/right direction
 * @param reverseStraight whether to reverse the forwards/backwards direction
 * @param reverseTurn whether to reverse the turning left/right direction
 */
class DriverControlled(
    private val gamepad: Gamepad,
    override val requirements: List<Subsystem> = arrayListOf(),
    override val interruptible: Boolean = true,
    private val pov: POV = POV.ROBOT_CENTRIC,
    private val reverseStrafe: Boolean = false,
    private val reverseStraight: Boolean = true,
    private val reverseTurn: Boolean = false
) : Command() {

    enum class POV {
        ROBOT_CENTRIC,
        FIELD_CENTRIC,
        DRIVER_CENTRIC
    }

    override val _isDone = false

    /**
     * Calculates and sets the robot's drive power
     */
    override fun execute() {
        val drivePower: Pose2d
        if (pov != POV.ROBOT_CENTRIC) {
            val angle: Double = if (gamepad.left_stick_x != 0.0f)
                atan(gamepad.left_stick_y / gamepad.left_stick_x).toDouble()
            else 90.0.toRadians

            var adjustedAngle = angle - drive.poseEstimate.heading
            if (pov == POV.DRIVER_CENTRIC) {
                if (color == Constants.Color.BLUE) {
                    adjustedAngle -= 90.0.toRadians
                }
                else {
                    adjustedAngle += 90.0.toRadians
                }
            }
            val totalPower = sqrt(gamepad.left_stick_y.pow(2) + gamepad.left_stick_x.pow(2))
            drivePower = Pose2d(
                if (reverseStraight) -1 else { 1 } * totalPower * sin(adjustedAngle),
                if (reverseStrafe) -1 else { 1 } * totalPower * cos(adjustedAngle),
                if (reverseTurn) -1 else { 1 } * (gamepad.right_stick_x).toDouble()
            )
        }
        else {
            drivePower = Pose2d(
                if (reverseStraight) -1 else { 1 } * (gamepad.left_stick_y).toDouble(),
                if (reverseStrafe) -1 else { 1 } * (gamepad.left_stick_x).toDouble(),
                if (reverseTurn) -1 else { 1 } * (gamepad.right_stick_x).toDouble()
            )
        }

        drive.setDrivePower(drivePower * drive.driverSpeed)
    }
}