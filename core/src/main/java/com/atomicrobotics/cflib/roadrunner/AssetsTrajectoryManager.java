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
package com.atomicrobotics.cflib.roadrunner;

import androidx.annotation.Nullable;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.config.TrajectoryConfig;
import com.acmerobotics.roadrunner.trajectory.config.TrajectoryConfigManager;
import com.acmerobotics.roadrunner.trajectory.config.TrajectoryGroupConfig;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Set of utilities for loading trajectories from assets (the plugin save location).
 */
public class AssetsTrajectoryManager {

    /**
     * Loads the group config.
     */
    public static @Nullable
    TrajectoryGroupConfig loadGroupConfig() {
        try {
            InputStream inputStream = AppUtil.getDefContext().getAssets().open(
                    "trajectory/" + TrajectoryConfigManager.GROUP_FILENAME);
            return TrajectoryConfigManager.loadGroupConfig(inputStream);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Loads a trajectory config with the given name.
     */
    public static @Nullable TrajectoryConfig loadConfig(String name) {
        try {
            InputStream inputStream = AppUtil.getDefContext().getAssets().open(
                    "trajectory/" + name + ".yaml");
            return TrajectoryConfigManager.loadConfig(inputStream);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Loads a trajectory builder with the given name.
     */
    public static @Nullable TrajectoryBuilder loadBuilder(String name) {
        TrajectoryGroupConfig groupConfig = loadGroupConfig();
        TrajectoryConfig config = loadConfig(name);
        if (groupConfig == null || config == null) {
            return null;
        }
        return config.toTrajectoryBuilder(groupConfig);
    }

    /**
     * Loads a trajectory with the given name.
     */
    public static @Nullable Trajectory load(String name) {
        TrajectoryBuilder builder = loadBuilder(name);
        if (builder == null) {
            return null;
        }
        return builder.build();
    }
}
