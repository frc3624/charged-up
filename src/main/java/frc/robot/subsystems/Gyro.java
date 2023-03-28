// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyro extends SubsystemBase {
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	/** Creates a new Gyro. */
	public Gyro() {
		gyro.calibrate();;
	}
	public double getAngle() {
		return gyro.getAngle();
	}
	public double getRate() {
		return gyro.getRate();
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
