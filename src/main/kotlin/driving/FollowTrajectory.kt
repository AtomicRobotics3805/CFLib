package org.firstinspires.ftc.teamcode.commandFramework.driving

import com.acmerobotics.roadrunner.drive.DriveSignal
import org.firstinspires.ftc.teamcode.commandFramework.Command
import org.firstinspires.ftc.teamcode.commandFramework.Constants.drive
import org.firstinspires.ftc.teamcode.commandFramework.driving.drivers.Driver
import org.firstinspires.ftc.teamcode.commandFramework.subsystems.Subsystem
import org.firstinspires.ftc.teamcode.commandFramework.trajectories.ParallelTrajectory

/**
 * This command follows a preexisting trajectory.
 *
 * @param trajectory the trajectory to follow
 * @param requirements any Subsystems used by this command
 * @param interruptible whether this command can be interrupted or not
 */
@Suppress("unused")
class FollowTrajectory(
    val trajectory: ParallelTrajectory,
    override val requirements: List<Subsystem> = arrayListOf(),
    override val interruptible: Boolean = true
) : Command() {

    override val _isDone: Boolean
        get() = !drive.follower.isFollowing()

    /**
     * Sets the robot's current position to the start of the trajectory (should this be removed?), then tells the
     * follower to start following the trajectory.
     */
    override fun start() {
        drive.poseEstimate = trajectory.trajectory.start()
        drive.follower.followTrajectory(trajectory.trajectory)
    }

    /**
     * Updates the drive signal
     */
    override fun execute() {
        drive.setDriveSignal(drive.follower.update(drive.poseEstimate))
    }

    override fun end(interrupted: Boolean) {
        drive.setDriveSignal(DriveSignal())
    }
}