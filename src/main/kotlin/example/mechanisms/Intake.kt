package org.firstinspires.ftc.teamcode.commandFramework.example.mechanisms

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.teamcode.commandFramework.Constants.opMode
import org.firstinspires.ftc.teamcode.commandFramework.Command
import org.firstinspires.ftc.teamcode.commandFramework.subsystems.PowerMotor
import org.firstinspires.ftc.teamcode.commandFramework.subsystems.Subsystem

/**
 * This class is an example of an intake controlled by a single motor. Its first two commands, start and stop, turn the
 * motor off and on. The third command, switch, starts it if it's off and stops if it's on. The switch command is
 * particularly useful during TeleOp.
 * If you want, you can also use this class for other mechanisms that also involve one motor, especially those that
 * don't require encoders.
 * To use this class, copy it into the proper package and change the two constants.
 */
@Config
@Suppress("Unused", "MemberVisibilityCanBePrivate")
object Intake : Subsystem {

    // configurable constants
    @JvmField
    var NAME = "intake"
    @JvmField
    var SPEED = 1.0
    @JvmField
    var DIRECTION = DcMotorSimple.Direction.FORWARD

    // commands
    val start: Command
        get() = PowerMotor(intakeMotor, SPEED, requirements = listOf(this))
    val stop: Command
        get() = PowerMotor(intakeMotor, 0.0, requirements = listOf(this))

    // motor
    private lateinit var intakeMotor: DcMotorEx

    /**
     * Initializes the intakeMotor, sets its mode to RUN_WITHOUT_ENCODER, and sets its direction to the DIRECTION
     * variable.
     */
    override fun initialize() {
        intakeMotor = opMode.hardwareMap.get(DcMotorEx::class.java, NAME)
        intakeMotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        intakeMotor.direction = DIRECTION
    }
}