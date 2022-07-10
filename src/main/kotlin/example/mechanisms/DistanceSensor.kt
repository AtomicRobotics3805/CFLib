package org.firstinspires.ftc.teamcode.commandFramework.example.mechanisms

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.AnalogInput
import org.firstinspires.ftc.teamcode.commandFramework.Constants
import org.firstinspires.ftc.teamcode.commandFramework.subsystems.Subsystem

/**
 * This is a general example of how to use a distance sensor in a subsystem. It doesn't have any
 * commands, but it does have voltage and distance values that can be used by other subsystems.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
@Config
object DistanceSensor : Subsystem {

    @JvmField
    var NAME = "distanceSensor"

    val voltage: Double
        get() = distanceSensor.voltage
    val distance: Double
        get() = voltageToDistance(voltage)

    private lateinit var distanceSensor: AnalogInput

    /**
     * Initializes the distanceSensor.
     */
    override fun initialize() {
        distanceSensor = Constants.opMode.hardwareMap.get(AnalogInput::class.java, NAME)
    }

    private fun voltageToDistance(voltage: Double) = voltage * 30.0
}