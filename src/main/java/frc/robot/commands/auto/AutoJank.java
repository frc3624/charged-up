// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drive;
import frc.robot.subsystems.dumpy.Dumper;
import io.github.oblarg.oblog.annotations.Config;

public class AutoJank extends CommandBase {
	private Drive drive;
	private Timer timer = new Timer();
	private Dumper dump;
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
		dump.dump();
		Timer.delay(1);
		dump.dump();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		leftMiddle();
	}
	@Config.ToggleButton

	public void leftMiddle() {
		if (timer.get() < 3)
			drive.arcadeDrive(0, 0);
		else if (timer.get() < 4.5)
			drive.arcadeDrive(0, -1);
		else if (timer.get() < 5.2)
			drive.arcadeDrive(.3, 0);
		else
			drive.arcadeDrive(0, 0);
	}
	public void rightMiddle() {

	}
	public void farRight() {

	}
	public void middle() {

	}
	public void farLeft() {

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
