package org.firstinspires.ftc.teamcode.commandFramework.driving

import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.qualcomm.robotcore.hardware.PIDFCoefficients

/**
 * This contains all of the constants used in every kind of drivetrain (tank, Mecanum, swerve, etc.)
 */
@Suppress("PropertyName")
interface DriveConstants {

    val TICKS_PER_REV: Double
    val MAX_RPM: Double
    val MOTOR_VEL_PID: PIDFCoefficients
    val IS_RUN_USING_ENCODER: Boolean
    val kV: Double
    val kA: Double
    val kStatic: Double
    val WHEEL_RADIUS: Double
    val GEAR_RATIO: Double
    val TRACK_WIDTH: Double
    val MAX_VEL: Double
    val MAX_ACCEL: Double
    val MAX_ANG_VEL: Double
    val MAX_ANG_ACCEL: Double
    val TRANSLATIONAL_PID: PIDCoefficients
    val HEADING_PID: PIDCoefficients
    val DRIVER_SPEEDS: List<Double>

    /**
     * Converts encoder ticks to inches
     */
    fun encoderTicksToInches(ticks: Double): Double {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV
    }

    /**
     * Converts revs per minute to inches per second
     */
    fun rpmToVelocity(rpm: Double): Double {
        return rpm * GEAR_RATIO * 2 * Math.PI * WHEEL_RADIUS / 60.0
    }

    /**
     * Automatically calculates the fixed feedforward constant for the motor velocity
     */
    fun getMotorVelocityF(ticksPerSecond: Double): Double {
        // see https://docs.google.com/document/d/1tyWrXDfMidwYyP_5H4mZyVgaEswhOC35gvdmP-V-5hA/edit#heading=h.61g9ixenznbx
        return 32767 / ticksPerSecond
    }
}