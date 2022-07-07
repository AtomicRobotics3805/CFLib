package com.atomicrobotics.cflib.visualization

import com.noahbres.meepmeep.MeepMeep
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder
import com.noahbres.meepmeep.roadrunner.trajectorysequence.SequenceSegment
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySegment
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence
import com.atomicrobotics.cflib.CommandGroup
import com.atomicrobotics.cflib.Constants
import com.atomicrobotics.cflib.driving.DriveConstants
import com.atomicrobotics.cflib.driving.FollowTrajectory
import com.atomicrobotics.cflib.driving.drivers.Driver
import com.atomicrobotics.cflib.trajectories.TrajectoryFactory

/**
 * This visualizer uses the MeepMeep library by acmerobotics to visualize robots' trajectories. It
 * uses a command routine filled with FollowTrajectory() commands, since that's how the robot
 * follows trajectories in real life. Using this technique, the any changes to the routines will
 * affect both the visualizer and real life simultaneously. Multiple robots can be simulated at the
 * same time. To do this, just call addRobot() more than once.
 */
object MeepMeepVisualizer {

    val robots = mutableListOf<Triple<Driver, () -> CommandGroup, Constants.Color>>()

    /**
     * Runs the application, creating a window and simulating the robots' paths. The addRobot()
     * function must be run at least once first.
     * @param trajectoryFactory the TrajectoryFactory containing all of the robots' trajectories and
     *                          start positions
     * @param windowSize the size of the window in pixels (600 by default)
     */
    fun run(trajectoryFactory: TrajectoryFactory, windowSize: Int = 600) {
        val meepMeep = MeepMeep(windowSize)
        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
            .setDarkMode(true)
            .setBackgroundAlpha(0.95f)
        robots.forEach {
            Constants.drive = it.first
            Constants.color = it.third
            trajectoryFactory.initialize()
            val constants: DriveConstants = it.first.constants
            val botBuilder: DefaultBotBuilder = DefaultBotBuilder(meepMeep)
                .setConstraints(
                    constants.MAX_VEL, constants.MAX_ACCEL,
                    constants.MAX_ANG_VEL, constants.MAX_ANG_ACCEL,
                    constants.TRACK_WIDTH
                ).setColorScheme(
                    if (it.third == Constants.Color.RED) ColorSchemeRedDark()
                    else ColorSchemeBlueDark()
                )
            meepMeep.addEntity(botBuilder.followTrajectorySequence(
                TrajectorySequence(
                    routineToSegmentList(it.second.invoke())
                )
            ))
        }
        meepMeep.start()

    }

    /**
     * Adds a robot to be simulated. This can be used multiple times if you wish to add multiple
     * robots.
     * @param driver the drivetrain for this robot
     * @param routine a lambda that returns a routine containing FollowTrajectory() commands
     * @param color the robot's color, either Constants.Color.BLUE or Constants.Color.RED
     */
    fun addRobot(driver: Driver, routine: () -> CommandGroup, color: Constants.Color) {
        robots.add(Triple(driver, routine, color))
    }

    /**
     * Translates a routine into a list of sequence segments that can be used by MeepMeep
     * @param routine a routine containing FollowTrajectory() commands
     * @return a list of sequence segments
     */
    private fun routineToSegmentList(routine: CommandGroup): List<SequenceSegment> {
        val trajectories = arrayListOf<SequenceSegment>()
        for (command in routine.commands) {
            if (command is FollowTrajectory) {
                trajectories.add(TrajectorySegment(command.trajectory.trajectory))
            }
            if (command is CommandGroup) {
                trajectories.addAll(routineToSegmentList(command))
            }
        }
        return trajectories
    }
}