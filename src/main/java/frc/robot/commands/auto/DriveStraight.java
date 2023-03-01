// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drive;

public class DriveStraight extends CommandBase {
	private Drive drive;
	private final Timer timer = new Timer();
	private double time;
	private double speed;
	/** Creates a new DriveStraight. */
	public DriveStraight(Drive drive, double time, double speed) {
		addRequirements(drive);
		this.drive = drive;
		this.time = time;
		this.speed = speed;
		timer.start();
		// Use addRequirements() here to declare subsystem dependencies.
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		System.out.println(timer.get());
		if (timer.get() < time)
			drive.arcadeDrive(0, speed);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		drive.arcadeDrive(0, 0);
		// timer.stop();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
