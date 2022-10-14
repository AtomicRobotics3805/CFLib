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
package com.atomicrobotics.cflib.example.controls

import com.atomicrobotics.cflib.CommandScheduler
import com.atomicrobotics.cflib.Constants
import com.atomicrobotics.cflib.controls.Controls
import com.atomicrobotics.cflib.example.mechanisms.Claw
import com.atomicrobotics.cflib.example.mechanisms.Lift

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