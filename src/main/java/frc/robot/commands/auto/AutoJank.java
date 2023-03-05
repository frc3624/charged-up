// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drive;
import frc.robot.subsystems.dumpy.Dumper;

public class AutoJank extends CommandBase {
	private Drive drive;
	private Timer timer = new Timer();
	private Dumper dump = new Dumper();
	private final double EPSILON = .00001;
	/** Creates a new AutoJank. */
	public AutoJank(Drive drive, Dumper dump) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.drive = drive;
		this.dump = dump;
		addRequirements(dump);
		addRequirements(drive);
		timer.start();
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {

	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		if (timer.get() < 1) {
			dump.dump();
			Timer.delay(1);
			dump.dump();
		} else if (timer.get() < 3)
			drive.arcadeDrive(0, -1);
		else if (drive.getAngle() - 5 >= EPSILON)
			drive.arcadeDrive(0, -.4);
		else
			drive.arcadeDrive(0, 0);
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
