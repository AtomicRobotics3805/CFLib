package org.firstinspires.ftc.teamcode.commandFramework.driving.localizers

import com.acmerobotics.roadrunner.localization.Localizer
import org.firstinspires.ftc.teamcode.commandFramework.subsystems.Subsystem

interface Localizer : Localizer, Subsystem {

    override fun periodic() { update() }
}