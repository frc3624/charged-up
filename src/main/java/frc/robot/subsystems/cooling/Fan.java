// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.cooling;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.PistonSettings.*;
public class Fan extends SubsystemBase {
	protected final PWMVictorSPX fan = new PWMVictorSPX(FAN);
	protected boolean wasOn = false;
	/**
	 * {@summary} Constructor for Fan Object. Fan uses PWMVictorSPX motor controller
	 * to run. Fan speed initially set to 0.
	 */
	public Fan() {
		fan.set(0);
	}
	/**
	 * @param compressor
	 *            Runs fan when compressor is running and 60 seconds after
	 */
	public void coolingRoutine(Compressor compressor) {
		fan.set(1);
	}
	/**
	 * @return Returns the current state of the fan
	 */
	public boolean isEnabled() {
		return (fan.get() > -1);
	}
}
