// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

	public interface GlobalSettings {
		public final int XBOX_ID = 0;
		public final int PCM_ID = 20;
	}

	public interface AutoSettings extends GlobalSettings {
		public final double kS = -.59426, kV = 2.9369, kA = 9.9175, kSAngular = 0.37161, kVAngular = 1.9639,
				kAAngular = 0.85077;
		public final double TRACK_WIDTH = Units.inchesToMeters(18.75);
		public final double GEAR_REDUCTION = 14.17;
		public final double WHEEL_DIAMETER = Units.inchesToMeters(6);

		// weird value? get more data?
		public static double kPDriveVel = 0.1;

		// auto stuff (clean up later)
		public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
				DriveSettings.TRACK_WIDTH);

		// tune this up later
		public static double kMaxSpeed = 2.7, kMaxAcceleration = 0.8;

		// Reasonable baseline values for a RAMSETE follower in units of meters and
		// seconds
		public static final double kRamseteB = 2;
		public static final double kRamseteZeta = 0.7;
		public static final double kEncoderDistancePerTick = 0.00992857142;
	}

	public interface DriveSettings extends AutoSettings {
		public final int LEFT_MASTER = 1, LEFT_FOLLOW = 2, RIGHT_MASTER = 3, RIGHT_FOLLOW = 4;
	}

	public interface PistonSettings extends GlobalSettings {
		public final boolean DEFAULT_PISTON_STATE = false;
		public final int DUMP = 0, TRAP = 1;
	}

	public interface LimelightSettings {
		public final int SERVO = 9;
	}
}
