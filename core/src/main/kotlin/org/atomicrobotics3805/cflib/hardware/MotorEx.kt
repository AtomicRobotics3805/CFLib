package org.atomicrobotics3805.cflib.hardware

import com.qualcomm.robotcore.hardware.DcMotorEx
import org.atomicrobotics3805.cflib.Constants
import kotlin.math.PI

class MotorEx(
    val name: () -> String,
    val type: MotorType = MotorType.ANDYMARK_NEVEREST,
    private val ratio: Double = 1.0
) {

    val ticksPerRev: Double
        get() = type.TICKS_PER_REVOLUTION * ratio

    val motor: DcMotorEx
        get() {
            requireNotNull(_motor) { "Did not initialize the \"${name.invoke()}\" motor"}
            return _motor!!
        }
    private var _motor: DcMotorEx? = null

    constructor(name: String, type: MotorType, ratio: Double) : this({ name }, type, ratio)

    fun initialize() {
        _motor = Constants.opMode.hardwareMap.get(DcMotorEx::class.java, name.invoke())
    }

    fun ticksToInches(ticks: Int, diameter: Double): Double =
        (ticks / ratio / type.TICKS_PER_REVOLUTION) * diameter * PI

    fun inchesToTicks(inches: Double, diameter: Double): Int =
        (inches * ratio * type.TICKS_PER_REVOLUTION / diameter / PI).toInt()

    enum class MotorType(
        val TICKS_PER_REVOLUTION: Int,
        val RPM: Double,
        val STALL_TORQUE_OZIN: Double
    ) {
        TETRIX_MAX_TORQUENADO(24, 6000.0, 11.666667),
        ANDYMARK_NEVEREST(28, 6600.0, 8.75),
        MODERN_ROBOTICS_MATRIX(28, 6000.0, 20.45),
        REV_ROBOTICS_HD_HEX(28, 6000.0, 14.8693),
        REV_ROBOTICS_CORE_HEX(4, 9000.0, 6.2939)
    }
}