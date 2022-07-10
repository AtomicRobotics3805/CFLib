package org.firstinspires.ftc.teamcode.commandFramework.opmodes

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.commandFramework.Command
import org.firstinspires.ftc.teamcode.commandFramework.CommandScheduler
import org.firstinspires.ftc.teamcode.commandFramework.Constants
import org.firstinspires.ftc.teamcode.commandFramework.TelemetryController
import org.firstinspires.ftc.teamcode.commandFramework.driving.drivers.Driver
import org.firstinspires.ftc.teamcode.commandFramework.subsystems.Subsystem
import org.firstinspires.ftc.teamcode.commandFramework.trajectories.TrajectoryFactory

/**
 * All Competition Autonomous OpModes should inherit from this class. It performs several tasks that
 * all Auto programs need to do. Each competition OpMode only needs to pass parameters to the
 * constructor - it doesn't need to have a body.
 * @param color the color of the alliance
 * @param trajectoryFactory the trajectory factory that contains start positions and trajectories
 * @param mainRoutine a lambda returning the main routine that needs to be performed after the play
 *                    button is pressed. This routine should be declared in a separate Routines
 *                    class.
 * @param initRoutine a lambda returning the routine that needs to be performed during
 *                    initialization. This routine should also be declared in a separate Routines
 *                    class.
 * @param drive the robot's drivetrain subsystem
 * @param subsystems each of the subsystems used by the robot. One of these should be a drivetrain
 *                   and the others should be mechanisms.
 */
@Suppress("unused")
abstract class AutonomousOpMode(private val color: Constants.Color,
                                private val trajectoryFactory: TrajectoryFactory,
                                private val mainRoutine: (() -> Command),
                                private val initRoutine: (() -> Command)? = null,
                                private val drive: Driver,
                                private vararg val subsystems: Subsystem
) : LinearOpMode() {

    override fun runOpMode() {
        try {
            // setting constants
            Constants.opMode = this
            Constants.color = color
            Constants.drive = drive
            // initialize trajectories & start positions
            trajectoryFactory.initialize()
            // this both registers & initializes the subsystems
            CommandScheduler.registerSubsystems(TelemetryController, drive, *subsystems)
            // if there is a routine that's supposed to be performed on init, then do it
            if (initRoutine != null) CommandScheduler.scheduleCommand(initRoutine.invoke())
            // wait for start
            while (!isStarted && !isStopRequested) {
                CommandScheduler.run()
            }
            // do the main routine
            CommandScheduler.scheduleCommand(mainRoutine.invoke())
            // wait for stop
            while (opModeIsActive()) {
                CommandScheduler.run()
            }
        } catch (e: Exception) {
            // we have to catch the exception so that we can still cancel and unregister
            TelemetryController.telemetry.addLine("Error Occurred!")
            TelemetryController.telemetry.addLine(e.message)
            // have to update telemetry since CommandScheduler won't call it anymore
            TelemetryController.periodic()
        } finally {
            // cancels all commands and unregisters all gamepads & subsystems
            CommandScheduler.cancelAll()
            CommandScheduler.unregisterAll()
        }
    }
}