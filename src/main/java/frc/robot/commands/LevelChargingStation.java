// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Gyro;

public class LevelChargingStation extends CommandBase {
	Gyro gyro = new Gyro();
	private final Drive drive;
	// private final CommandXboxController button;
	public LevelChargingStation(Gyro gyro, Drive drive) {
		this.gyro = gyro;
		this.drive = drive;
		addRequirements(drive);
	}

	@Override
	public void initialize() {
		gyro.adjustAngle(drive);
	}

	@Override
	public void execute() {

	}
}
