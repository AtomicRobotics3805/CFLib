package org.firstinspires.ftc.teamcode.commandFramework.trajectories

import com.acmerobotics.roadrunner.geometry.Pose2d
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference
import org.firstinspires.ftc.robotcore.external.navigation.Orientation
import org.firstinspires.ftc.teamcode.commandFramework.Constants.Color.BLUE
import org.firstinspires.ftc.teamcode.commandFramework.Constants.color

val Double.inchesToMm get() = this * 25.4
val Double.mmToInches get() = this / 25.4
val Double.toRadians get() = (Math.toRadians(this))
val Double.switchColorAngle get () = (if (color == BLUE) this else 360 - this)
val Double.switchColor get () = (if (color == BLUE) this else this * -1)
fun Pose2d(matrix: OpenGLMatrix) = Pose2d(
    matrix.translation.get(0).toDouble().mmToInches,
    // TODO("Figure out where this is matrix.get(1) (y) or matrix.get(2) (z)")
    matrix.translation.get(1).toDouble().mmToInches,
    Orientation.getOrientation(matrix, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES)
        .thirdAngle.toDouble().toRadians
)