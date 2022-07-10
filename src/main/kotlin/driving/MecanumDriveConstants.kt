package org.firstinspires.ftc.teamcode.commandFramework.driving

import com.qualcomm.robotcore.hardware.DcMotorSimple

@Suppress("PropertyName")
interface MecanumDriveConstants : DriveConstants {

    val LATERAL_MULTIPLIER: Double
    val DRIFT_MULTIPLIER: Double
    val DRIFT_TURN_MULTIPLIER: Double
    val LEFT_FRONT_DIRECTION: DcMotorSimple.Direction
    val LEFT_BACK_DIRECTION: DcMotorSimple.Direction
    val RIGHT_FRONT_DIRECTION: DcMotorSimple.Direction
    val RIGHT_BACK_DIRECTION: DcMotorSimple.Direction
    val LEFT_FRONT_NAME: String
    val LEFT_BACK_NAME: String
    val RIGHT_FRONT_NAME: String
    val RIGHT_BACK_NAME: String
}