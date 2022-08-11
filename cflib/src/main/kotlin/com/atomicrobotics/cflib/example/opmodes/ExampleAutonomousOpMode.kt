package com.atomicrobotics.cflib.example.opmodes

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.atomicrobotics.cflib.Constants
import com.atomicrobotics.cflib.driving.drivers.MecanumDrive
import com.atomicrobotics.cflib.driving.localizers.TwoWheelOdometryLocalizer
import com.atomicrobotics.cflib.example.drive.ExampleMecanumDriveConstants
import com.atomicrobotics.cflib.example.localizers.ExampleOdometryConstants
import com.atomicrobotics.cflib.example.mechanisms.Claw
import com.atomicrobotics.cflib.example.mechanisms.Lift
import com.atomicrobotics.cflib.example.routines.ExampleRoutines
import com.atomicrobotics.cflib.example.trajectoryfactory.ExampleTrajectoryFactory
import com.atomicrobotics.cflib.opmodes.AutonomousOpMode

/**
 * This class is an example of how you can create an Autonomous OpMode. Everything is handled by
 * the AutonomousOpMode parent class, so all you have to do is pass in the constructor parameters.
 */
@Disabled
@Autonomous(name = "Example Auto OpMode")
class ExampleAutonomousOpMode : AutonomousOpMode(
    Constants.Color.BLUE,
    ExampleTrajectoryFactory,
    { ExampleRoutines.mainRoutine },
    { ExampleRoutines.initializationRoutine },
    MecanumDrive(
        ExampleMecanumDriveConstants,
        TwoWheelOdometryLocalizer(ExampleOdometryConstants())
    ) { Pose2d() },
    Lift,
    Claw
)