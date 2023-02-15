// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cooling;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.cooling.Fan;
public class CoolCompressor extends CommandBase {
	private final Fan fan;
	private final Compressor compressor;
	/**
	 * @param fan
	 * @param compressor
	 *            Class used for fan routine.
	 */
	public CoolCompressor(Fan fan, Compressor compressor) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.fan = fan;
		this.compressor = compressor;
		addRequirements(fan);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		fan.coolingRoutine(compressor);
		System.out.println(fan.isEnabled());
	}
}
