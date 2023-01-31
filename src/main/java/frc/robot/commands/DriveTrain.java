// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drive;

public class DriveTrain extends CommandBase {
	private Drive drive;
	private CommandXboxController xboxController;

	public DriveTrain(Drive drive, CommandXboxController xboxController) {
		this.drive = drive;
		this.xboxController = xboxController;
		addRequirements(drive);
	}

	@Override
	public void execute() {
		drive.arcadeDrive(xboxController.getLeftY(), xboxController.getRightX());
	}
}
