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
package com.atomicrobotics.cflib.visualization

import com.noahbres.meepmeep.MeepMeep
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder
import com.noahbres.meepmeep.roadrunner.trajectorysequence.sequencesegment.SequenceSegment
import com.noahbres.meepmeep.roadrunner.trajectorysequence.sequencesegment.TrajectorySegment
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence
import com.atomicrobotics.cflib.CommandGroup
import com.atomicrobotics.cflib.Constants
import com.atomicrobotics.cflib.driving.DriveConstants
import com.atomicrobotics.cflib.driving.FollowTrajectory
import com.atomicrobotics.cflib.driving.drivers.Driver
import com.atomicrobotics.cflib.trajectories.TrajectoryFactory

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