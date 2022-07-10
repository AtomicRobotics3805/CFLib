package org.firstinspires.ftc.teamcode.commandFramework.driving

import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.PIDFCoefficients

@Suppress("PropertyName")
interface TankDriveConstants : DriveConstants {

    val LEFT_DIRECTION: DcMotorSimple.Direction
    val RIGHT_DIRECTION: DcMotorSimple.Direction
    val LEFT_NAME: String
    val RIGHT_NAME: String
}