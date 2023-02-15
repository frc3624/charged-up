// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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

	public interface DriveSettings {
		public final int LEFT_MASTER = 1, LEFT_SLAVE = 2, RIGHT_MASTER = 3, RIGHT_SLAVE = 4;

		// MUST UPDATE THESE WITH SYSID
		public final double kV = 0.0, kA = 0.0, kVAngular = 0.0, kAAngular = 0.0;
		public final double TRACK_WIDTH = Units.inchesToMeters(18.75);
		public final double GEAR_REDUCTION = 13.33;
		public final double WHEEL_DIAMETER = Units.inchesToMeters(6);
	}

	public interface PistonSettings extends GlobalSettings {
		public final boolean DEFAULT_PISTON_STATE = false;
		public final int DUMP = 0, TRAP = 1, FAN = 8;
	}

	public interface LimelightSettings {
		public final int SERVO = 9;
	}
}
