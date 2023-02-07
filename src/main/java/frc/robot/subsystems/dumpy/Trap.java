// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.dumpy;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PistonSettings;

public class Trap extends SubsystemBase implements PistonSettings {

	private final Solenoid piston = new Solenoid(PCM_ID, PneumaticsModuleType.REVPH, TRAP);

	public Trap() {
		piston.set(false);
	}

	/**
	 * Release Trapdoor and expel game piece from Dumpy
	 */
	public void toggleDoor() {
		piston.toggle();
	}

}
