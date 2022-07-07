package com.atomicrobotics.cflib.example.localizers

import com.atomicrobotics.cflib.driving.localizers.TwoWheelOdometryConstants

/**
 * Example constants for a TwoWheelOdometryLocalizer. Contains hardware & spacial information,
 * whether the encoders should be reversed, their multipliers, and their names.
 */
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
    @JvmField
    var _TICKS_PER_REV = 8192 // REV through bore encoder
    @JvmField
    var _WHEEL_RADIUS = 0.688975 // rotacaster wheels // in
    @JvmField
    var _GEAR_RATIO = 1.0 // output (wheel) speed / input (encoder) speed
    @JvmField
    var _PARALLEL_REVERSED = true
    @JvmField
    var _PERPENDICULAR_REVERSED = true
    @JvmField
    var _X_MULTIPLIER = 1.0 // higher value makes the robot think that X is increasing faster
    @JvmField
    var _Y_MULTIPLIER = 1.0 // higher value makes the robot think that Y is increasing faster
    @JvmField
    var _PERPENDICULAR_NAME = "RF"
    @JvmField
    var _PARALLEL_NAME = "LF"

    override val PARALLEL_X: Double
        get() = _PARALLEL_X
    override val PARALLEL_Y: Double
        get() = _PARALLEL_Y
    override val PERPENDICULAR_X: Double
        get() = _PERPENDICULAR_X
    override val PERPENDICULAR_Y: Double
        get() = _PERPENDICULAR_Y
    override val TICKS_PER_REV: Int
        get() = _TICKS_PER_REV
    override val WHEEL_RADIUS: Double
        get() = _WHEEL_RADIUS
    override val GEAR_RATIO: Double
        get() = _GEAR_RATIO
    override val PARALLEL_REVERSED: Boolean
        get() = _PARALLEL_REVERSED
    override val PERPENDICULAR_REVERSED: Boolean
        get() = _PERPENDICULAR_REVERSED
    override val X_MULTIPLIER: Double
        get() = _X_MULTIPLIER
    override val Y_MULTIPLIER: Double
        get() = _Y_MULTIPLIER
    override val PERPENDICULAR_NAME: String
        get() = _PERPENDICULAR_NAME
    override val PARALLEL_NAME: String
        get() = _PARALLEL_NAME
}