package org.firstinspires.ftc.teamcode.commandFramework.example.mechanisms

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.AnalogInput
import org.firstinspires.ftc.teamcode.commandFramework.Constants
import org.firstinspires.ftc.teamcode.commandFramework.subsystems.Subsystem

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