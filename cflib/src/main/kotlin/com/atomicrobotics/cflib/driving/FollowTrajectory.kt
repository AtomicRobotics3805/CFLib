package com.atomicrobotics.cflib.driving

import com.acmerobotics.roadrunner.drive.DriveSignal
import com.atomicrobotics.cflib.Command
import com.atomicrobotics.cflib.Constants.drive
import com.atomicrobotics.cflib.subsystems.Subsystem
import com.atomicrobotics.cflib.trajectories.ParallelTrajectory

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