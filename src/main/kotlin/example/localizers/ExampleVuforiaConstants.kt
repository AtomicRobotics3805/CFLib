package org.firstinspires.ftc.teamcode.commandFramework.example.localizers

import org.firstinspires.ftc.teamcode.commandFramework.driving.localizers.VuforiaConstants
import org.firstinspires.ftc.teamcode.commandFramework.trajectories.inchesToMm

@Suppress("PropertyName")
class ExampleVuforiaConstants : VuforiaConstants {

    @JvmField
    var _VUFORIA_KEY = " AZ2jk6P/////AAABmck8NCyjWkCGvLdpx9HZ1kxI2vQPDlzN9vJnqy69nXRjvoXgBCEWZasRnd1hFjBpRiSXw4G4JwDFsk3kNSVko2UkuCgbi/RsiODF76MtldIi6YZGfrRMZTICMKwTanuOysh4Cn9Xd9nZzCpDiLAPLsUtKoj/DdBUn0gJuARMglUPW7/qirgtk0xI232ttZpXhgh9ya8R8LxnH+UTCCFtEaQft2ru0Tv+30Un82gG1uEzcrMc/8F3lefedcOTrelPQx8xUD8cME9dj99b5oZWfM60b36/xdswhYF7pygskPtXCS28j81xWKHGNhr5s8xL91cbKOovDzdJYdfVIILZnL1sjdbtN8zW4mULOYHwO4ur"
    @JvmField
    var _CAMERA_FORWARD_DISPLACEMENT = 0.0.inchesToMm.toFloat()
    @JvmField
    var _CAMERA_VERTICAL_DISPLACEMENT = 0.0.inchesToMm.toFloat()
    @JvmField
    var _CAMERA_LEFT_DISPLACEMENT = 0.0.inchesToMm.toFloat()
    @JvmField
    var _DELAY_TIME_MILLIS = 0.0

    override val VUFORIA_KEY: String
        get() = _VUFORIA_KEY
    override val CAMERA_FORWARD_DISPLACEMENT: Float
        get() = _CAMERA_FORWARD_DISPLACEMENT
    override val CAMERA_VERTICAL_DISPLACEMENT: Float
        get() = _CAMERA_VERTICAL_DISPLACEMENT
    override val CAMERA_LEFT_DISPLACEMENT: Float
        get() = _CAMERA_LEFT_DISPLACEMENT
    override val DELAY_TIME_MILLIS: Double
        get() = _DELAY_TIME_MILLIS
}