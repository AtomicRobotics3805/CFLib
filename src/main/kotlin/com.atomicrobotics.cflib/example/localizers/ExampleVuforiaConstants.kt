/*
    Copyright (c) 2022 Atomic Robotics (https://atomicrobotics3805.org)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.atomicrobotics.cflib.example.localizers

import com.atomicrobotics.cflib.driving.localizers.VuforiaConstants
import com.atomicrobotics.cflib.trajectories.inchesToMm

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