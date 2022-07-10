package org.firstinspires.ftc.teamcode.commandFramework.example.opmodes

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.commandFramework.Constants
import org.firstinspires.ftc.teamcode.commandFramework.driving.drivers.MecanumDrive
import org.firstinspires.ftc.teamcode.commandFramework.driving.localizers.MecanumDriveWheelLocalizer
import org.firstinspires.ftc.teamcode.commandFramework.driving.localizers.TwoWheelOdometryLocalizer
import org.firstinspires.ftc.teamcode.commandFramework.example.controls.ExampleControls
import org.firstinspires.ftc.teamcode.commandFramework.example.drive.ExampleMecanumDriveConstants
import org.firstinspires.ftc.teamcode.commandFramework.example.localizers.ExampleOdometryConstants
import org.firstinspires.ftc.teamcode.commandFramework.example.mechanisms.Claw
import org.firstinspires.ftc.teamcode.commandFramework.example.mechanisms.Lift
import org.firstinspires.ftc.teamcode.commandFramework.example.routines.ExampleRoutines
import org.firstinspires.ftc.teamcode.commandFramework.example.trajectoryfactory.ExampleTrajectoryFactory
import org.firstinspires.ftc.teamcode.commandFramework.opmodes.TeleOpMode

/**
 * This class is an example of how you can create an TeleOp OpMode. Everything is handled by the
 * TeleOpMode parent class, so all you have to do is pass in the constructor parameters.
 */
@Disabled
@TeleOp(name = "Example TeleOp OpMode")
class ExampleTeleOpMode : TeleOpMode(
    ExampleControls,
    Constants.Color.UNKNOWN,
    ExampleTrajectoryFactory,
    { ExampleRoutines.teleOpStartRoutine },
    null,
    MecanumDrive(
        ExampleMecanumDriveConstants,
        TwoWheelOdometryLocalizer(ExampleOdometryConstants()),
    ) { Constants.endPose ?: Pose2d() },
    Lift,
    Claw
)