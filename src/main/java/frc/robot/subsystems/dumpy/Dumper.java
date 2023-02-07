// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.dumpy;

import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Dumper extends SubsystemBase {

	// private final DoubleSolenoid piston = new DoubleSolenoid(PCM_ID,
	// PneumaticsModuleType.REVPH, DUMP_IN, DUMP_OUT);
	private final Solenoid piston = new Solenoid(PCM_ID, PneumaticsModuleType.REVPH, DUMP);
	public Dumper() {
		piston.set(false);
	}

	/**
	 * Release Dumpy and expel game piece from Dumpy
	 */
	public void dump() {
		piston.set(!(piston.get()));
	}

}
