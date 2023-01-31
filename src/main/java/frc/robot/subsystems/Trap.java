// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class Trap extends SubsystemBase {
	/** Creates a new Trap. */
	private final DoubleSolenoid piston = new DoubleSolenoid(PCM_ID, PneumaticsModuleType.REVPH, TRAP_IN, TRAP_OUT);
	public Trap() {
		piston.set(Value.kReverse);
	}
	public void trap() {
		if (piston.get() == Value.kForward)
			piston.set(Value.kReverse);
		else if (piston.get() == Value.kReverse)
			piston.set(Value.kForward);
		else
			piston.set(Value.kReverse);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
