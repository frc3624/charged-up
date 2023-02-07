// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.SERVO;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

	private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	private final Servo servo = new Servo(SERVO);

	public double getServoAngle() {
		return servo.getAngle();
	}

	public enum LimelightAngle {
		INTAKE_ANGLE(45), DRIVE_ANGLE(5);

		private final int angle;

		private LimelightAngle(int angle) {
			this.angle = angle;
		}

		public int getAngle() {
			return angle;
		}
	}

	public enum LedMode {
		CURRENT(0), OFF(1), BLINK(2), ON(3);

		private final int value;

		private LedMode(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public void setLedMode(LedMode ledMode) {
		table.getEntry("ledMode").setNumber(ledMode.getValue());
	}

	public void setPosition(LimelightAngle angle) {
		servo.set(angle.getAngle());
	}

}
