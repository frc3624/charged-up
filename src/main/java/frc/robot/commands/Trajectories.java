// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;


public class Trajectories  {
  /** Creates a new Trajectories. */
  public Trajectories() {}
    public static Trajectory loadTrajectoryFromFile(String filename) {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve("paths/" + filename);
      Trajectory trajectory = null;

      try {
          trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
      } catch (IOException e) {
          DriverStation.reportError("Unable to open trajectory: " + filename, e.getStackTrace());
      }

    return trajectory;
}
  
}
