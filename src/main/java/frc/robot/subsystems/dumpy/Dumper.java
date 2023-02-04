// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.dumpy;

import static frc.robot.Constants.DUMP_IN;
import static frc.robot.Constants.DUMP_OUT;
import static frc.robot.Constants.PCM_ID;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Dumper extends SubsystemBase {

	private final DoubleSolenoid piston = new DoubleSolenoid(PCM_ID, PneumaticsModuleType.REVPH, DUMP_IN, DUMP_OUT);

	public Dumper() {
		piston.set(Value.kReverse);
	}

	/**
	 * Release Dumpy and expel game piece from Dumpy
	 */
	public void dump() {
		if (piston.get() == Value.kForward) {
			piston.set(Value.kReverse);
		} else if (piston.get() == Value.kReverse) {
			piston.set(Value.kForward);
		} else {
			piston.set(Value.kReverse);
		}
	}

}