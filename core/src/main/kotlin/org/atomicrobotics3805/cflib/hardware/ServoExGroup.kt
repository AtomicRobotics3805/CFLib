package org.atomicrobotics3805.cflib.hardware

import com.qualcomm.robotcore.hardware.Servo

open class MultiServoEx(
    vararg val servos: ServoConversion
): ServoEx(servos[0].name, servos[0].type, servos[0].speedOverride) {


    override val servo = servos[0].servo

    override fun getManufacturer() = servos[0].getManufacturer()
    override fun getDeviceName() = servos[0].getDeviceName()
    override fun getConnectionInfo() = servos[0].getConnectionInfo()
    override fun getVersion() = servos[0].getVersion()
    override fun getController() = servos[0].getController()
    override fun getPortNumber() = servos[0].getPortNumber()
    override fun getDirection() = servos[0].getDirection()
    override fun getPosition() = servos[0].getPosition()
    override fun setDirection(direction: Servo.Direction?) {
        servos.forEach {
            it.setDirection(it.directionTranslator.invoke(direction))
        }
    }
    override fun setPosition(position: Double) {
        servos.forEach {
            it.setPosition(it.positionTranslator.invoke(position))
        }
    }
    override fun scaleRange(min: Double, max: Double) {
        servos.forEach {
            val pair = it.scaleRangeTranslator.invoke(min, max)
            it.scaleRange(pair.first, pair.second)
        }
    }
    override fun resetDeviceConfigurationForOpMode() {
        servos.forEach {
            it.resetDeviceConfigurationForOpMode()
        }
    }
    override fun close() {
        servos.forEach {
            it.close()
        }
    }

    override fun initialize() {
        servos.forEach {
            it.initialize()
        }
    }
}

class ServoConversion(
    name: () -> String, type: ServoType? = null, speedOverride: Double? = null,
    val positionTranslator: (Double) -> Double,
    val directionTranslator: (Servo.Direction?) -> Servo.Direction? = { it },
    val scaleRangeTranslator: (Double, Double) -> Pair<Double, Double> = { min, max -> Pair(min, max)}
): ServoEx(name, type, speedOverride) {

    constructor(
        name: String, type: ServoType? = null, speedOverride: Double? = null,
        positionTranslator: (Double) -> Double,
        directionTranslator: (Servo.Direction?) -> Servo.Direction? = { it },
        scaleRangeTranslator: (Double, Double) -> Pair<Double, Double> = { min, max -> Pair(min, max)}):
            this({ name }, type, speedOverride, positionTranslator,
                directionTranslator, scaleRangeTranslator)
}