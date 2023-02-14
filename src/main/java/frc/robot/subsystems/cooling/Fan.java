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
	protected boolean wasOn = false;
	public Fan() {
		fan.set(false);
	}
	/**
	 * @param compressor
	 *            Runs fan when compressor is running and 60 seconds after
	 */
	public void coolingRoutine(Compressor compressor) {
		double current = compressor.getCurrent();
		System.out.println(compressor.getCurrent());
		if (current > 15 || compressor.isEnabled()) {
			// turns it on
			fan.toggle();
			wasOn = true;
		} else if (wasOn) {
			Timer.delay(60);
			// turns it off
			fan.toggle();
			wasOn = false;
		} else {
			fan.set(false);
		}
	}
	public boolean isEnabled() {
		return fan.get();
	}
	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
