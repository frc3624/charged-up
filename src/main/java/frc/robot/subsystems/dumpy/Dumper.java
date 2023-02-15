// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.dumpy;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PistonSettings;

public class Dumper extends SubsystemBase implements PistonSettings {

	private final Solenoid piston = new Solenoid(PCM_ID, PneumaticsModuleType.REVPH, DUMP);
	/**
	 * Subsystem for dumptruck portion of the robot Includes method to dump the
	 * dumptruck
	 */
	public Dumper() {
		piston.set(false);
	}

	/**
	 * Release Dumpy and expel game piece from Dumpy
	 */
	public void dump() {
		piston.toggle();
	}

}
