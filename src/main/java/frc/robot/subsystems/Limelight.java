// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class Limelight extends SubsystemBase {
	/** Creates a new Limelight. */
	// private final NetworkTable table =
	// NetworkTableInstance.getDefault().getTable("limelight");
	private final Servo servo = new Servo(SERVO);

	public void setPosition(double yPosition) {
		servo.setPosition(yPosition);
	}

	@Override

	public void periodic() {
		// This method will be called once per scheduler run
	}
}
