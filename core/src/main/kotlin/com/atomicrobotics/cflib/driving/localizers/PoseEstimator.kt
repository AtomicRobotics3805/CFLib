package com.atomicrobotics.cflib.driving.localizers

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.util.ElapsedTime
import kotlin.math.abs

/**
 * This class uses an OdometryLocalizer and a VuforiaLocalizer to determine position. Both
 * localizers have flaws that the other covers. The odometry localizer determines current position
 * relative to previous position, meaning that in long games it can have accumulating error. The
 * VuforiaLocalizer doesn't have accumulating error, but it can't be used if a VuTarget is not in
 * sight. It also has a slight delay. The PoseEstimator combines them to get the best of both.
 * @param odometryLocalizer the OdometryLocalizer, technically can be an instance of any
 *                          class that extends Localizer
 * @param vuforiaLocalizer the VuforiaLocalizer, has to be an instance of the VuforiaLocalizer class
 */
class PoseEstimator(
    private val odometryLocalizer: Localizer,
    private val vuforiaLocalizer: VuforiaLocalizer
) : Localizer {
    override var poseEstimate: Pose2d
        get() = odometryPositions.last().second + absoluteAdjustment + manualAdjustment
        set(value) {
            manualAdjustment = value - poseEstimate
        }
    override val poseVelocity: Pose2d?
        get() = odometryLocalizer.poseVelocity

    private val odometryPositions: ArrayList<Pair<Double, Pose2d>> = arrayListOf(Pair(0.0, Pose2d()))
    private var lastAbsolutePosition = Pose2d()
    private var absoluteAdjustment = Pose2d()
    private var manualAdjustment = Pose2d()

    private val timer = ElapsedTime()

    /**
     * Resets the timer
     */
    override fun initialize() {
        timer.reset()
    }

    /**
     * Updates both localizers and uses both poseEstimates create a more accurate poseEstimate
     */
    override fun update() {
        odometryLocalizer.update()
        vuforiaLocalizer.update()
        odometryPositions.add(Pair(timer.milliseconds(), odometryLocalizer.poseEstimate))
        if (vuforiaLocalizer.poseEstimate != lastAbsolutePosition) {
            addAbsoluteMeasurement(lastAbsolutePosition, vuforiaLocalizer.constants.DELAY_TIME_MILLIS)
            lastAbsolutePosition = vuforiaLocalizer.poseEstimate
        }
    }

    /**
     * Saves an absolute (i.e. Vuforia) measurement. This is not combined with update because
     * Vuforia does not always see a new target every time the update method is run
     */
    private fun addAbsoluteMeasurement(position: Pose2d, delayTimeMillis: Double) {
        var nearestTime: Double? = null
        var nearestPair: Pair<Double, Pose2d>? = null
        for (pair in odometryPositions) {
            val time: Double = abs(timer.milliseconds() - delayTimeMillis - pair.first)
            if (nearestTime == null || time < nearestTime) {
                nearestPair = pair
                nearestTime = time
            }
        }
        if (nearestPair != null)
            absoluteAdjustment = position - nearestPair.second
    }
}