package org.firstinspires.ftc.teamcode.commandFramework.example.visualization

import org.firstinspires.ftc.teamcode.commandFramework.Constants
import org.firstinspires.ftc.teamcode.commandFramework.driving.drivers.MecanumDrive
import org.firstinspires.ftc.teamcode.commandFramework.driving.localizers.TwoWheelOdometryLocalizer
import org.firstinspires.ftc.teamcode.commandFramework.example.drive.ExampleMecanumDriveConstants
import org.firstinspires.ftc.teamcode.commandFramework.example.localizers.ExampleOdometryConstants
import org.firstinspires.ftc.teamcode.commandFramework.example.trajectoryfactory.ExampleTrajectoryFactory
import org.firstinspires.ftc.teamcode.commandFramework.sequential
import org.firstinspires.ftc.teamcode.commandFramework.visualization.MeepMeepVisualizer

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