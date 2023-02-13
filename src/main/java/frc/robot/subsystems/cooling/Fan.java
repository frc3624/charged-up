// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.cooling;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.PistonSettings.*;

public class Fan extends SubsystemBase {
	/** Creates a new Cooling. */
	private final Solenoid fan = new Solenoid(PneumaticsModuleType.REVPH, FAN);
	public Fan() {
		fan.set(false);
	}

	public void coolingRoutine(Compressor compressor) {
		boolean wasOn = false;
		System.out.println(compressor.isEnabled());
		if (compressor.isEnabled()) {
			fan.set(true);
			wasOn = true;
		} else if (wasOn) {
			Timer.delay(60);
			fan.set(false);
			wasOn = false;
		} else {
			fan.set(false);
		}
	}
	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
