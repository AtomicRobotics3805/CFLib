package com.atomicrobotics.cflib.driving.localizers

import com.atomicrobotics.cflib.subsystems.Subsystem

/**
 * This interface is currently broken. We are working to resolve the issue.
 */
interface Localizer : Subsystem {

    override fun periodic() { update() }
}