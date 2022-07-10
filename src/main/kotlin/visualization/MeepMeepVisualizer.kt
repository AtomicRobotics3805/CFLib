package org.firstinspires.ftc.teamcode.commandFramework.visualization

import com.noahbres.meepmeep.MeepMeep
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder
import com.noahbres.meepmeep.roadrunner.trajectorysequence.SequenceSegment
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySegment
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence
import org.firstinspires.ftc.teamcode.commandFramework.CommandGroup
import org.firstinspires.ftc.teamcode.commandFramework.Constants
import org.firstinspires.ftc.teamcode.commandFramework.driving.DriveConstants
import org.firstinspires.ftc.teamcode.commandFramework.driving.FollowTrajectory
import org.firstinspires.ftc.teamcode.commandFramework.driving.drivers.Driver
import org.firstinspires.ftc.teamcode.commandFramework.trajectories.TrajectoryFactory

object MeepMeepVisualizer {

    val robots = mutableListOf<Triple<Driver, () -> CommandGroup, Constants.Color>>()

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

    fun addRobot(driver: Driver, routine: () -> CommandGroup, color: Constants.Color) {
        robots.add(Triple(driver, routine, color))
    }

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