package com.atomicrobotics.cflib.driving

import com.qualcomm.robotcore.hardware.DcMotorSimple

@Suppress("PropertyName")
interface TankDriveConstants : DriveConstants {

    val LEFT_DIRECTION: DcMotorSimple.Direction
    val RIGHT_DIRECTION: DcMotorSimple.Direction
    val LEFT_NAME: String
    val RIGHT_NAME: String
}