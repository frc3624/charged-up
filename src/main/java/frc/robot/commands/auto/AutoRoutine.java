// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.drivetrain.Drive;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoRoutine extends SequentialCommandGroup {
	/** Creates a new AutoRoutine. */
	private Drive drive;
	private DriveStraight str1;
	private TurnRobot turn;
	private DriveStraight str2;
	public AutoRoutine(Drive drive, DriveStraight str1, DriveStraight str2, TurnRobot turn) {
		this.drive = drive;
		this.str1 = str1;
		this.str2 = str2;
		this.turn = turn;
		// str1.andThen(turn.andThen(str2));
		addCommands(str1.andThen(turn).andThen(str2));
	}

}
