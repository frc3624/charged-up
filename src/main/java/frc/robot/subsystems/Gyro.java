// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyro extends SubsystemBase {
	/** Creates a new Gyro. */
	private AHRS gyro = new AHRS();
	public Gyro() {
		gyro.calibrate();
	}
	public void adjustAngle(Drive drive) {
		if (gyro.getAngle() < -3) {
			drive.arcadeDrive(.6, 0);
		} else if (gyro.getAngle() > 3) {
			drive.arcadeDrive(-.6, 0);
		} else {
			drive.arcadeDrive(0, 0);
		}
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
