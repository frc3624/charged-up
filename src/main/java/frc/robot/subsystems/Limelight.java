// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LimelightSettings;

public class Limelight extends SubsystemBase implements LimelightSettings {

	private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	private final Servo servo = new Servo(SERVO);
	/**
	 * Contains preset intake angle and drive angle. Also contains accessor method
	 * for said angles.
	 */
	public enum LimelightAngle {
		INTAKE_ANGLE(35), DRIVE_ANGLE(5);

		private final int angle;

		private LimelightAngle(int angle) {
			this.angle = angle;
		}

		public int getAngle() {
			return angle;
		}
	}
	/**
	 * Contains present settings for the Limelight LED. Also contains accessor
	 * method for the current limelight setting
	 */
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
	/**
	 * Sets the LED Mode of the Limelight
	 */
	public void setLedMode(LedMode ledMode) {
		table.getEntry("ledMode").setNumber(ledMode.getValue());
	}
	/**
	 * Sets the position of the limelight
	 */
	public void setPosition(LimelightAngle angle) {
		servo.setAngle(angle.getAngle());
	}

}
