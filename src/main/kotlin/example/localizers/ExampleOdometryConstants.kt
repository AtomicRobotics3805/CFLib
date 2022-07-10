package org.firstinspires.ftc.teamcode.commandFramework.example.localizers

import org.firstinspires.ftc.teamcode.commandFramework.driving.localizers.TwoWheelOdometryConstants

@Suppress("PropertyName")
class ExampleOdometryConstants : TwoWheelOdometryConstants {
    @JvmField
    var _PARALLEL_X = 0.0 // in; forward offset of the parallel wheel
    @JvmField
    var _PARALLEL_Y = 0.0 // in; left offset of the parallel wheel
    @JvmField
    var _PERPENDICULAR_X = 0.0 // in; forward offset of the perpendicular wheel
    @JvmField
    var _PERPENDICULAR_Y = 0.0 // in; left offset of the perpendicular wheel

    override val PARALLEL_X: Double
        get() = _PARALLEL_X
    override val PARALLEL_Y: Double
        get() = _PARALLEL_Y
    override val PERPENDICULAR_X: Double
        get() = _PERPENDICULAR_X
    override val PERPENDICULAR_Y: Double
        get() = _PERPENDICULAR_Y
}