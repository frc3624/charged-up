// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.dumpy;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.dumpy.Trap;

public class ShiftTrap extends CommandBase {
	private final Trap trap;
	/**
	 * @param trap
	 *            Command used to open and close trap door.
	 */
	public ShiftTrap(Trap trap) {
		this.trap = trap;
		addRequirements(trap);
	}

	@Override
	public void initialize() {
		trap.toggleDoor();
	}
}
