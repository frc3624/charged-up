// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.drivetrain.Drive;

public class GoalToStation extends SequentialCommandGroup {
	public GoalToStation(Drive drive) {
        Trajectory trajectory = Trajectories.loadTrajectoryFromFile("GoalToStation.wpilib.json");
        
        //addCommands(new DriveTrajectoryCommand(drivetrain, trajectory));
    }
}

