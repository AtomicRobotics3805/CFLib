package org.atomicrobotics3805.cflib.hardware

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.PIDCoefficients
import com.qualcomm.robotcore.hardware.PIDFCoefficients
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit

class MotorExGroup(
    vararg val motors: MotorEx
): MotorEx(motors[0].name, motors[0].type, motors[0].ratio, motors[0]._direction) {


    override fun setMotorEnable() {
        motors.forEach {
            it.setMotorEnable()
        }
    }
    override fun setMotorDisable() {
        motors.forEach {
            it.setMotorDisable()
        }
    }
    override fun isMotorEnabled() = motors[0].isMotorEnabled()
    override fun setVelocity(angularRate: Double) {
        motors.forEach {
            it.setVelocity(angularRate)
        }
    }
    override fun setVelocity(angularRate: Double, unit: AngleUnit) {
        motors.forEach {
            it.setVelocity(angularRate, unit)
        }
    }
    override fun getVelocity() = motors[0].getVelocity()
    override fun getVelocity(unit: AngleUnit) = motors[0].getVelocity(unit)
    @Deprecated("Use setPIDFCoefficients instead")
    override fun setPIDCoefficients(runMode: DcMotor.RunMode, pidCoefficients: PIDCoefficients) {
        motors.forEach {
            it.setPIDCoefficients(runMode, pidCoefficients)
        }
    }
    override fun setPIDFCoefficients(runMode: DcMotor.RunMode, pidfCoefficients: PIDFCoefficients) {
        motors.forEach {
            it.setPIDFCoefficients(runMode, pidfCoefficients)
        }
    }
    override fun setVelocityPIDFCoefficients(p: Double, i: Double, d: Double, f: Double) {
        motors.forEach {
            it.setVelocityPIDFCoefficients(p, i, d, f)
        }
    }
    override fun setPositionPIDFCoefficients(p: Double) {
        motors.forEach {
            it.setPositionPIDFCoefficients(p)
        }
    }
    override fun getPIDFCoefficients(runMode: DcMotor.RunMode) = motors[0].getPIDFCoefficients(runMode)
    override fun setTargetPositionTolerance(tolerance: Int) {
        motors.forEach {
            it.setTargetPositionTolerance(tolerance)
        }
    }
    override fun getTargetPositionTolerance() = motors[0].getTargetPositionTolerance()
    override fun getCurrent(unit: CurrentUnit) = motors[0].getCurrent(unit)
    override fun getCurrentAlert(unit: CurrentUnit) = motors[0].getCurrentAlert(unit)
    override fun setCurrentAlert(current: Double, unit: CurrentUnit) {
        motors.forEach {
            it.setCurrentAlert(current, unit)
        }
    }
    override fun isOverCurrent() = motors[0].isOverCurrent()

    // DcMotor Methods
    override fun getMotorType() = motor.motorType
    override fun setMotorType(motorType: MotorConfigurationType?) {
        motors.forEach {
            it.setMotorType(motorType)
        }
    }
    override fun getController() = motor.controller
    override fun getPortNumber() = motor.portNumber
    override fun setZeroPowerBehavior(zeroPowerBehavior: DcMotor.ZeroPowerBehavior?) {
        motors.forEach {
            it.setZeroPowerBehavior(zeroPowerBehavior)
        }
    }
    override fun getZeroPowerBehavior() = motor.zeroPowerBehavior
    @Deprecated(
        """This method is deprecated in favor of direct use of
            {@link #setZeroPowerBehavior(ZeroPowerBehavior) setZeroPowerBehavior()} and
            {@link #setPower(double) setPower()}."""
    )
    override fun setPowerFloat() {
        motors.forEach{
            it.setPowerFloat()
        }
    }
    override fun getPowerFloat() = motor.powerFloat
    override fun setTargetPosition(position: Int) {
        motors.forEach {
            it.setTargetPosition(position)
        }
    }
    override fun getTargetPosition() = motor.targetPosition
    override fun isBusy() = motor.isBusy
    override fun getCurrentPosition() = motor.currentPosition
    override fun setMode(mode: DcMotor.RunMode?) {
        motors.forEach {
            it.setMode(mode)
        }
    }
    override fun getMode() = motor.mode

    // DcMotorSimple methods
    override fun setDirection(direction: DcMotorSimple.Direction?) {
        motors.forEach {
            it.setDirection(direction)
        }
    }
    override fun getDirection() = motor.direction
    override fun setPower(power: Double) {
        motors.forEach {
            it.setPower(power)
        }
    }
    override fun getPower() = motor.power


    override fun initialize() {
        for (motor in motors) {
            motor.initialize()
        }
    }
}