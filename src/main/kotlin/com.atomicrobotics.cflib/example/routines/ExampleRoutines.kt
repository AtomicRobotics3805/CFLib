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
package com.atomicrobotics.cflib.example.routines

import com.atomicrobotics.cflib.Command
import com.atomicrobotics.cflib.Constants.drive
import com.atomicrobotics.cflib.example.mechanisms.Claw
import com.atomicrobotics.cflib.example.mechanisms.Lift
import com.atomicrobotics.cflib.example.trajectoryfactory.ExampleTrajectoryFactory
import com.atomicrobotics.cflib.parallel
import com.atomicrobotics.cflib.sequential

/**
 * This class is an example of how to create routines. Routines are essentially just groups of
 * commands that can be run either one at a time (sequentially) or all at once (in parallel).
 */
object ExampleRoutines {

    val initializationRoutine: Command
        get() = parallel {
            +Lift.toLow
            +Claw.close
        }

    val mainRoutine: Command
        get() = sequential {
            +drive.followTrajectory(ExampleTrajectoryFactory.startToHubFront)
            +Lift.toHigh
            +Claw.open
            +parallel {
                +Lift.toLow
                +drive.followTrajectory(ExampleTrajectoryFactory.hubFrontToPark)
            }
        }

    val teleOpStartRoutine: Command
        get() = parallel {
            +Lift.toLow
            +Claw.open
        }
}