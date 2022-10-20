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
@file:Suppress("PropertyName")

package com.atomicrobotics.cflib.driving.localizers

import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.localization.TwoTrackingWheelLocalizer
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.atomicrobotics.cflib.Constants
import com.atomicrobotics.cflib.roadrunner.Encoder


/**
 * Determines position relative to previous position based on odometry wheels. Odometry wheels are
 * wheels that aren't attached to motors but are attached to encoders that can determine how much
 * the wheels have rotated. Based on how much they've rotated, we can determine the robot's
 * position. The reason why there are four constants assigned outside of the class is because we
 * need them to be outside of the class to use them in the constructor.
 *
 * This class is currently broken. We are working to resolve the issue.
 */
@Config
class TwoWheelOdometryLocalizer(val constants: TwoWheelOdometryConstants) : TwoTrackingWheelLocalizer(listOf(
    Pose2d(constants.PARALLEL_X, constants.PARALLEL_Y, 0.0),
    Pose2d(constants.PERPENDICULAR_X, constants.PERPENDICULAR_Y, Math.toRadians(90.0))
)), Localizer {

    @JvmField
    var TICKS_PER_REV = 8192 // REV through bore encoder
    @JvmField
    var WHEEL_RADIUS = 0.688975 // rotacaster wheels // in
    @JvmField
    var GEAR_RATIO = 1.0 // output (wheel) speed / input (encoder) speed
    @JvmField
    var PARALLEL_REVERSED = true
    @JvmField
    var PERPENDICULAR_REVERSED = true
    @JvmField
    var X_MULTIPLIER = 1.0
    @JvmField
    var Y_MULTIPLIER = 1.0

    lateinit var perpendicularEncoder: Encoder
    lateinit var parallelEncoder: Encoder

    /**
     * Initializes the encoders & sets their direction
     */
    override fun initialize() {
        perpendicularEncoder = Encoder(Constants.opMode.hardwareMap.get(DcMotorEx::class.java, constants.PERPENDICULAR_NAME))
        parallelEncoder = Encoder(Constants.opMode.hardwareMap.get(DcMotorEx::class.java, constants.PARALLEL_NAME))

        if (PERPENDICULAR_REVERSED) perpendicularEncoder.direction = Encoder.Direction.REVERSE
        if (PARALLEL_REVERSED) parallelEncoder.direction = Encoder.Direction.REVERSE
    }

    /**
     * Returns the drive heading since this localizer only uses two wheels
     * @return the heading in radians
     */
    override fun getHeading(): Double {
        return Constants.drive.rawExternalHeading
    }

    /**
     * Returns the drive heading velocity since this localizer only uses two wheels
     * @return the radian/sec heading velocity
     */
    override fun getHeadingVelocity(): Double? {
        return Constants.drive.externalHeadingVelocity
    }

    /**
     * Returns how many inches each wheel has turned, multiplied by X_MULTIPLIER & Y_MULTIPLIER
     * @return list of how much each wheel has turned in inches
     */
    override fun getWheelPositions(): List<Double> {
        return listOf(
            encoderTicksToInches(parallelEncoder.currentPosition.toDouble()) * X_MULTIPLIER,
            encoderTicksToInches(perpendicularEncoder.currentPosition.toDouble()) * Y_MULTIPLIER
        )
    }

    /**
     * Returns the inch/sec velocity of each wheel, multiplied by X_MULTIPLIER & Y_MULTIPLIER
     * @return list of inch/sec wheel velocities
     */
    override fun getWheelVelocities(): List<Double> {
        // If your encoder velocity can exceed 32767 counts / second (such as the REV Through Bore and other
        //  competing magnetic encoders), change Encoder.getRawVelocity() to Encoder.getCorrectedVelocity() to enable a
        //  compensation method
        return listOf(
            encoderTicksToInches(perpendicularEncoder.rawVelocity) * X_MULTIPLIER,
            encoderTicksToInches(parallelEncoder.rawVelocity) * Y_MULTIPLIER
        )
    }

    /**
     * Translates encoder ticks to inches. Remember that each pulse gets counted as four ticks.
     * @param ticks the encoder ticks to translate
     * @return the number of inches
     */
    private fun encoderTicksToInches(ticks: Double): Double {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV
    }
}

interface TwoWheelOdometryConstants {
    val PARALLEL_X: Double // in; forward offset of the parallel wheel
    val PARALLEL_Y: Double  // in; left offset of the parallel wheel
    val PERPENDICULAR_X: Double // in; forward offset of the perpendicular wheel
    val PERPENDICULAR_Y: Double // in; left offset of the perpendicular wheel
    val PARALLEL_NAME: String
    val PERPENDICULAR_NAME: String
}