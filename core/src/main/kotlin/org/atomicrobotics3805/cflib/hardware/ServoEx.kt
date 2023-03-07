package org.atomicrobotics3805.cflib.hardware

import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.configuration.annotations.ServoType
import org.atomicrobotics3805.cflib.Constants

class ServoEx(
    val name: () -> String,
    val type: ServoType? = null,
    private val speedOverride: Double? = null
) {

    val speed: Double
        get() {
            if (speedOverride != null) {
                return speedOverride
            }
            if(type != null) {
                return 60 * type.RANGE_REVOLUTIONS / type.RPM
            }
            return 1.0
        }

    val servo: Servo
        get() {
            requireNotNull(_servo) { "Did not initialize the \"${name.invoke()}\" servo" }
            return _servo!!
        }
    private var _servo: Servo? = null

    constructor(name: String, type: ServoType? = null, speedOverride: Double? = null) : this(
        { name }, type, speedOverride
    )

    fun initialize() {
        _servo = Constants.opMode.hardwareMap.get(Servo::class.java, name.invoke())
    }

    // TODO: Complete this (IMPORTANT BEFORE NEXT RELEASE)
    enum class ServoType(
        val RPM: Double,
        val RANGE_REVOLUTIONS: Double,
        val STALL_TORQUE_OZIN: Double
    ) {

    }
}