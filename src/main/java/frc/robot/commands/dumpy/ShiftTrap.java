// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.dumpy;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.dumpy.Trap;

public class ShiftTrap extends CommandBase {
	/** Creates a new ShiftTrap. */
	private final Trap trap;
	public ShiftTrap(Trap trap) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.trap = trap;
		addRequirements(trap);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		trap.toggleDoor();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
