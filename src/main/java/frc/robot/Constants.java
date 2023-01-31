// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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

	public static final int kDriverControllerPort = 0;

	// DRIVE IDS
	public final static int LEFT_MASTER = 1, LEFT_SLAVE = 2, RIGHT_MASTER = 3, RIGHT_SLAVE = 4;

	// LIMELIGHT IDS
	public final static int SERVO = 0;

	// PCM ID AND PISTON POSITIONS
	public final static int PCM_ID = 0, DUMP_IN = 6, DUMP_OUT = 7, TRAP_IN = 8, TRAP_OUT = 9;

	// CONTROLLER IDS
	public final static int JOYSTICK_ID = 0;
	// public static final int BUTTON_A = 1, BUTTON_B = 2, BUTTON_X = 3, BUTTON_Y =
	// 4;
	// public static final int BUTTON_LB = 5, BUTTON_RB = 6;
}
