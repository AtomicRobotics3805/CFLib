package com.atomicrobotics.cflib.driving.localizers

import com.acmerobotics.roadrunner.localization.Localizer
import com.atomicrobotics.cflib.subsystems.Subsystem

/**
 * Every localizer in the framework inherits from this interface. It is a child of both Subsystem
 * and the RoadRunner localizer, meaning that its children must implement poseEstimate,
 * poseVelocity, and update(). The periodic() method from Subsystem simply runs the update() method.
 */
interface Localizer : Localizer, Subsystem {

    override fun periodic() { update() }
}