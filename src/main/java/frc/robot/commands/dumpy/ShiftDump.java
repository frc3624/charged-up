// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.dumpy;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.dumpy.Dumper;

public class ShiftDump extends CommandBase {
	private final Dumper dumpy;
	/**
	 * @param dumpy
	 *            Command used to lift and drop the dumper
	 */
	public ShiftDump(Dumper dumpy) {
		this.dumpy = dumpy;
		addRequirements(dumpy);
	}

	@Override
	public void initialize() {
		dumpy.dump();
	}
}
