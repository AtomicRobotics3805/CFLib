package com.atomicrobotics.cflib.driving

import com.qualcomm.robotcore.hardware.DcMotorSimple

/**
 * This interface contains all of the constants specific to tank drives (i.e. constants that are not
 * used by other drivetrains, such as Mecanum drives)
 */
@Suppress("PropertyName")
interface TankDriveConstants : DriveConstants {

    val LEFT_DIRECTION: DcMotorSimple.Direction
    val RIGHT_DIRECTION: DcMotorSimple.Direction
    val LEFT_NAME: String
    val RIGHT_NAME: String
}