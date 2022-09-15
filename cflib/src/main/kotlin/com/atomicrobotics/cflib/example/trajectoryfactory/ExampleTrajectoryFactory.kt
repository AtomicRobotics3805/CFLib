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
package com.atomicrobotics.cflib.example.trajectoryfactory

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.atomicrobotics.cflib.trajectories.*
import com.atomicrobotics.cflib.Constants

/**
 * This class contains all of the RoadRunner trajectories and start positions in the project. It's
 * used by the ExampleRoutines class. You can find how to use each of the possible trajectory
 * segments (like back and splineToSplineHeading) here:
 * https://learnroadrunner.com/trajectorybuilder-functions.html
 */
object ExampleTrajectoryFactory : TrajectoryFactory() {

    // start position declarations
    lateinit var hubFrontStartPose: Pose2d
    // trajectory declarations
    lateinit var startToHubFront: ParallelTrajectory
    lateinit var hubFrontToPark: ParallelTrajectory

    /**
     * Initializes the robot's start positions and trajectories. This is where the trajectories are
     * actually created.
     */
    override fun initialize() {
        super.initialize()
        // start positions
        hubFrontStartPose = Pose2d(-12.0, 63.0.switchColor, 90.0.switchColorAngle.toRadians)
        // trajectories
        startToHubFront = Constants.drive.trajectoryBuilder(hubFrontStartPose, hubFrontStartPose.heading + 180.0.switchColorAngle.toRadians)
            .back(21.0)
            .build()
        hubFrontToPark = Constants.drive.trajectoryBuilder(startToHubFront.trajectory.end(), startToHubFront.trajectory.end().heading + 270.0.switchColorAngle.toRadians)
            .splineToSplineHeading(Pose2d(20.0, 42.0.switchColor, 0.0.switchColorAngle.toRadians), 0.0.switchColorAngle.toRadians)
            .splineToSplineHeading(Pose2d(40.0, 42.0.switchColor, 0.0.switchColorAngle.toRadians), 0.0.switchColorAngle.toRadians)
            .build()
    }
}