// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class LevelChargingStation extends CommandBase {
	private AHRS gyro = new AHRS();
	private final Drive drive;
	// private final CommandXboxController button;
	public LevelChargingStation(Drive drive) {
		this.drive = drive;
		addRequirements(drive);
	}

	public void adjustAngle() {
		if (gyro.getAngle() < -3) {
			drive.arcadeDrive(.6, 0);
		} else if (gyro.getAngle() > 3) {
			drive.arcadeDrive(-.6, 0);
		} else {
			drive.arcadeDrive(0, 0);
		}
	}

	@Override
	public void initialize() {
		adjustAngle();
	}

	@Override
	public void execute() {

	}
}