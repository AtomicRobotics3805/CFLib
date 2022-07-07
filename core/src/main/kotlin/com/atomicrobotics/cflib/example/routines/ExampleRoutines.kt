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