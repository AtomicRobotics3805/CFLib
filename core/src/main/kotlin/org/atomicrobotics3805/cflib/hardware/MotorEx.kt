package org.atomicrobotics3805.cflib.hardware

import org.atomicrobotics3805.cflib.Constants.opMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotor.RunMode
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction
import com.qualcomm.robotcore.hardware.PIDCoefficients
import com.qualcomm.robotcore.hardware.PIDFCoefficients
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit
import kotlin.math.PI

open class MotorEx (
    val name: () -> String,
    val type: MotorType = MotorType.ANDYMARK_NEVEREST,
    val ratio: Double = 1.0,
    val _direction: Direction
) {

    val ticksPerRev: Double
        get() = type.TICKS_PER_REVOLUTION * ratio

    open val motor: DcMotorEx
        get() {
            requireNotNull(_motor) { "Did not initialize the \"${name.invoke()}\" motor" }
            return _motor!!
        }
    private var _motor: DcMotorEx? = null

    constructor(name: String, type: MotorType, ratio: Double, direction: Direction) :
            this({ name }, type, ratio, direction)

    open fun initialize() {
        _motor = opMode.hardwareMap.get(DcMotorEx::class.java, name.invoke())
        _motor!!.direction = _direction
    }

    fun ticksToInches(ticks: Int, diameter: Double): Double =
        (ticks / ratio / type.TICKS_PER_REVOLUTION) * diameter * PI

    fun inchesToTicks(inches: Double, diameter: Double): Int =
        (inches * ratio * type.TICKS_PER_REVOLUTION / diameter / PI).toInt()

    // DcMotorEx Methods
    open fun setMotorEnable() = motor.setMotorEnable()
    open fun setMotorDisable() = motor.setMotorDisable()
    open fun isMotorEnabled() = motor.isMotorEnabled
    open fun setVelocity(angularRate: Double) = motor.setVelocity(angularRate)
    open fun setVelocity(angularRate: Double, unit: AngleUnit) = motor.setVelocity(angularRate, unit)
    open fun getVelocity() = motor.velocity
    open fun getVelocity(unit: AngleUnit) = motor.getVelocity(unit)
    @Deprecated("Use setPIDFCoefficients instead")
    open fun setPIDCoefficients(runMode: DcMotor.RunMode, pidCoefficients: PIDCoefficients) =
        motor.setPIDCoefficients(runMode, pidCoefficients)
    open fun setPIDFCoefficients(runMode: DcMotor.RunMode, pidfCoefficients: PIDFCoefficients) =
        motor.setPIDFCoefficients(runMode, pidfCoefficients)
    open fun setVelocityPIDFCoefficients(p: Double, i: Double, d: Double, f: Double) =
        motor.setVelocityPIDFCoefficients(p, i, d, f)
    open fun setPositionPIDFCoefficients(p: Double) = motor.setPositionPIDFCoefficients(p)
    open fun getPIDFCoefficients(runMode: DcMotor.RunMode) = motor.getPIDFCoefficients(runMode)
    open fun setTargetPositionTolerance(tolerance: Int) {
        motor.targetPositionTolerance = tolerance
    }
    open fun getTargetPositionTolerance() = motor.targetPositionTolerance
    open fun getCurrent(unit: CurrentUnit) = motor.getCurrent(unit)
    open fun getCurrentAlert(unit: CurrentUnit) = motor.getCurrentAlert(unit)
    open fun setCurrentAlert(current: Double, unit: CurrentUnit) = motor.setCurrentAlert(current, unit)
    open fun isOverCurrent() = motor.isOverCurrent

    // DcMotor Methods
    open fun getMotorType() = motor.motorType
    open fun setMotorType(motorType: MotorConfigurationType?) {
        motor.motorType = motorType
    }
    open fun getController() = motor.controller
    open fun getPortNumber() = motor.portNumber
    open fun setZeroPowerBehavior(zeroPowerBehavior: ZeroPowerBehavior?) {
        motor.zeroPowerBehavior = zeroPowerBehavior
    }
    open fun getZeroPowerBehavior() = motor.zeroPowerBehavior
    @Deprecated(
        """This method is deprecated in favor of direct use of
            {@link #setZeroPowerBehavior(ZeroPowerBehavior) setZeroPowerBehavior()} and
            {@link #setPower(double) setPower()}."""
    )
    open fun setPowerFloat() = motor.setPowerFloat()
    open fun getPowerFloat() = motor.powerFloat
    open fun setTargetPosition(position: Int) {
        motor.targetPosition = position
    }
    open fun getTargetPosition() = motor.targetPosition
    open fun isBusy() = motor.isBusy
    open fun getCurrentPosition() = motor.currentPosition
    open fun setMode(mode: RunMode?) {
        motor.mode = mode
    }
    open fun getMode() = motor.mode

    // DcMotorSimple methods
    open fun setDirection(direction: Direction?) {
        motor.direction = direction
    }
    open fun getDirection() = motor.direction
    open fun setPower(power: Double) {
        motor.power = power
    }
    open fun getPower() = motor.power

    enum class MotorType(
        val TICKS_PER_REVOLUTION: Int,
        val RPM: Double,
        val STALL_TORQUE_OZIN: Double
    ) {
        TETRIX_MAX_TORQUENADO(24, 6000.0, 11.666667),
        ANDYMARK_NEVEREST(28, 6600.0, 8.75),
        MODERN_ROBOTICS_MATRIX(28, 6000.0, 20.45),
        GOBILDA_YELLOWJACKET(28, 6000.0, 20.45),
        REV_ROBOTICS_HD_HEX(28, 6000.0, 14.8693),
        REV_ROBOTICS_CORE_HEX(4, 9000.0, 6.2939),
    }
}
