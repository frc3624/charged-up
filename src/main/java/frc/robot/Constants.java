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
	public final static int LEFT_MASTER = 1, LEFT_SLAVE = 2, RIGHT_MASTER = 3, RIGHT_SLAVE = 4;

	public final static int PCM_ID = 0, GEARSHIFT_FORWARD_CHANNEL = 6, GEARSHIFT_REVERSE_CHANNEL = 7;

	public final static int JOYSTICK_ID = 0;
}
