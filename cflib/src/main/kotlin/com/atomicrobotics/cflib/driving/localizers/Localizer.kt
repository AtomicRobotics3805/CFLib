package com.atomicrobotics.cflib.driving.localizers

import com.atomicrobotics.cflib.subsystems.Subsystem

interface Localizer : Localizer, Subsystem {

    override fun periodic() { update() }
}