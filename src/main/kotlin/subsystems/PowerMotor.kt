package org.firstinspires.ftc.teamcode.commandFramework.subsystems

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.teamcode.commandFramework.Command

/**
 * This command powers a motor and sets it to a certain mode if one is supplied.
 *
 * @param motor the motor to power
 * @param power the power (-1.0 to 1.0) to set it to
 * @param mode the RunMode that the motor should be set to, if any
 * @param requirements any Subsystems used by this command
 * @param interruptible whether this command can be interrupted or not
 */
class PowerMotor(
    private val motor: DcMotorSimple,
    private val power: Double,
    private val mode: DcMotor.RunMode? = null,
    override val requirements: List<Subsystem> = arrayListOf(),
    override val interruptible: Boolean = true
) : Command() {

    override fun start() {
        if (mode != null && motor is DcMotor) {
            motor.mode = mode
        }
        motor.power = power
    }
}