package org.firstinspires.ftc.teamcode.commandFramework.example.controls

import org.firstinspires.ftc.teamcode.commandFramework.CommandScheduler
import org.firstinspires.ftc.teamcode.commandFramework.Constants
import org.firstinspires.ftc.teamcode.commandFramework.controls.Controls
import org.firstinspires.ftc.teamcode.commandFramework.example.mechanisms.Claw
import org.firstinspires.ftc.teamcode.commandFramework.example.mechanisms.Lift

/**
 * This class manages the controls for TeleOp OpModes. It gives examples of how to register
 * different commands. If you want to register a command, type a line into registerCommands with the
 * following format:
 * gamepad.button.command = Subsystem.command
 * For example, that could look like this:
 * gamepad1.a.startCommand = Lift.toHigh
 * If you used the line above, then whenever you pressed the a button on gamepad1 it would move the
 * lift to the high position.
 */
object ExampleControls : Controls() {

    /**
     * Registers commands on the gamepads.
     */
    override fun registerCommands() {
        // gamepad 1
        // this first command is a little bit different because it doesn't get activated by pressing
        // a button - rather, it's always active
        CommandScheduler.scheduleCommand(Constants.drive.driverControlled(Constants.opMode.gamepad1))
        gamepad1.a.pressedCommand = { Constants.drive.switchSpeed() }
        // gamepad 2
        gamepad2.dpadUp.pressedCommand = { Lift.toHigh }
        gamepad2.dpadDown.pressedCommand = { Lift.toLow }
        gamepad2.leftBumper.pressedCommand = { Lift.start }
        gamepad2.leftBumper.releasedCommand = { Lift.stop }
        gamepad2.rightBumper.pressedCommand = { Lift.reverse }
        gamepad2.rightBumper.releasedCommand = { Lift.stop }
        gamepad2.b.toggleCommands = arrayListOf({ Claw.open }, { Claw.close })
    }
}