// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.cooling;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Fan extends SubsystemBase {
	/** Creates a new Cooling. */
	// private final Solenoid fan = new Solenoid(PneumaticsModuleType.REVPH, FAN);
	protected final PWMVictorSPX fan = new PWMVictorSPX(8);
	protected boolean wasOn = false;
	public Fan() {
		// fan.set(1);
	}
	/**
	 * @param compressor
	 *            Runs fan when compressor is running and 60 seconds after
	 */
	public void coolingRoutine(Compressor compressor) {
		if (compressor.isEnabled()) {
			// turns it on
			fan.set(1);
			wasOn = true;
		} else if (wasOn) {
			Timer.delay(15);
			// turns it off
			fan.set(1);
			wasOn = false;
		} else {
			fan.set(0);
		}
	}
	public boolean isEnabled() {
		return true;
	}
	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
