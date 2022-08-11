package com.atomicrobotics.cflib.driving.localizers

import com.acmerobotics.roadrunner.localization.Localizer
import com.atomicrobotics.cflib.subsystems.Subsystem

/**
 * This interface is currently broken. We are working to resolve the issue.
 */
interface Localizer : Subsystem, Localizer {

    override fun periodic() { update() }
}