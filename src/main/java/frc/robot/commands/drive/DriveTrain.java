// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import frc.robot.subsystems.cooling.Fan;
import frc.robot.subsystems.drivetrain.Drive;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/** An example command that uses an example subsystem. */
public class DriveTrain extends CommandBase {
	@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
	private final Drive drive;
	private CommandXboxController xboxController;
	private Fan fan;
	private Compressor compressor;

	/**
	 * Creates a new ExampleCommand.
	 *
	 * @param subsystem
	 *            The subsystem used by this command.
	 */
	public DriveTrain(Drive drive, CommandXboxController xboxController, Fan fan, Compressor compressor) {
		this.drive = drive;
		this.xboxController = xboxController;
		this.fan = fan;
		this.compressor = compressor;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(drive);
		addRequirements(fan);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		drive.arcadeDrive(xboxController.getRightX(), -xboxController.getLeftY());
		fan.coolingRoutine(compressor);
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
