package org.firstinspires.ftc.teamcode.commandFramework.example.mechanisms

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.DigitalChannel
import org.firstinspires.ftc.teamcode.commandFramework.Command
import org.firstinspires.ftc.teamcode.commandFramework.Constants
import org.firstinspires.ftc.teamcode.commandFramework.subsystems.Subsystem

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