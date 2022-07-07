package com.atomicrobotics.cflib.example.visualization

import com.atomicrobotics.cflib.Constants
import com.atomicrobotics.cflib.driving.drivers.MecanumDrive
import com.atomicrobotics.cflib.driving.localizers.TwoWheelOdometryLocalizer
import com.atomicrobotics.cflib.example.drive.ExampleMecanumDriveConstants
import com.atomicrobotics.cflib.example.localizers.ExampleOdometryConstants
import com.atomicrobotics.cflib.example.trajectoryfactory.ExampleTrajectoryFactory
import com.atomicrobotics.cflib.sequential
import com.atomicrobotics.cflib.visualization.MeepMeepVisualizer

/**
 * This file adds a robot, its path, and its color to the MeepMeepVisualizer and runs the
 * visualizer. To run this file, right click on it in the project window on the left and click "Run
 * 'ExampleVisualizerKt'"
 */
fun main() {
    MeepMeepVisualizer.addRobot(
        MecanumDrive(
            ExampleMecanumDriveConstants,
            TwoWheelOdometryLocalizer(ExampleOdometryConstants())
        ) { ExampleTrajectoryFactory.hubFrontStartPose },
        { sequential {
            +Constants.drive.followTrajectory(ExampleTrajectoryFactory.startToHubFront)
            +Constants.drive.followTrajectory(ExampleTrajectoryFactory.hubFrontToPark)
        } },
        Constants.Color.BLUE
    )
    MeepMeepVisualizer.run(ExampleTrajectoryFactory)
}