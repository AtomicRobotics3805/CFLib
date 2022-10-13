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

import com.atomicrobotics.cflib.driving.localizers.TwoWheelOdometryConstants

@Suppress("PropertyName")
class ExampleOdometryConstants : TwoWheelOdometryConstants {
    @JvmField
    var _PARALLEL_X = 0.0 // in; forward offset of the parallel wheel
    @JvmField
    var _PARALLEL_Y = 0.0 // in; left offset of the parallel wheel
    @JvmField
    var _PERPENDICULAR_X = 0.0 // in; forward offset of the perpendicular wheel
    @JvmField
    var _PERPENDICULAR_Y = 0.0 // in; left offset of the perpendicular wheel
    @JvmField
    var _PARALLEL_NAME = "LF" // the name of the wheel that the parallel deadwheel encoder is plugged into
    @JvmField
    var _PERPENDICULAR_NAME = "LB" // the name of the wheel that the perpendicular deadwheel encoder is plugged into

    override val PARALLEL_X: Double
        get() = _PARALLEL_X
    override val PARALLEL_Y: Double
        get() = _PARALLEL_Y
    override val PERPENDICULAR_X: Double
        get() = _PERPENDICULAR_X
    override val PERPENDICULAR_Y: Double
        get() = _PERPENDICULAR_Y
    override val PARALLEL_NAME: String
        get() = _PARALLEL_NAME
    override val PERPENDICULAR_NAME: String
        get() = _PERPENDICULAR_NAME
}