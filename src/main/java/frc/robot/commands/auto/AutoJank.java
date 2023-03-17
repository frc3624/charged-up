// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drive;
import frc.robot.subsystems.dumpy.Dumper;
import frc.robot.subsystems.dumpy.Trap;

public class AutoJank extends CommandBase {
	private Drive drive;
	private Timer timer = new Timer();
	private Dumper dump;
	private NetworkTableInstance instance = NetworkTableInstance.getDefault();
	private int choice = (int) SmartDashboard.getNumber("Auto Position", 0);
	private Trap trap;
	// private NetworkTable table = instance.getTable("auto");
	// private final ShuffleboardTab tab = Shuffleboard.getTab("auto");

	// private final ComplexWidget leftMid = tab.add("leftMiddle", new
	// AutoLeftMiddle(drive));
	// private SendableChooser<CommandBase> auto = new
	// SendableChooser<CommandBase>();
	private final ShuffleboardTab tab = Shuffleboard.getTab("auto");
	// private GenericEntry leftMid = tab.add("Left Middle Routine",
	// false).getEntry();
	// private GenericEntry straight = tab.add("Straight", false).getEntry();

	/** Creates a new AutoJank. */
	public AutoJank(Drive drive, Dumper dump, Trap trap) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.drive = drive;
		this.dump = dump;
		this.trap = trap;
		addRequirements(dump);
		addRequirements(drive);
		addRequirements(trap);
		timer.start();
		// auto.addOption("leftMid", new AutoJank(drive, dump));
		// SmartDashboard.putData("Auto Modes",auto);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		System.out.println(choice);
		dump.dump();
		Timer.delay(1);
		dump.dump();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		decisionTree();
	}
	// @Config.ToggleButton

	public void decisionTree() {
		if (choice == 1) {
			leftMiddle();
		} else if (choice == 2) {
			trap.toggleDoor();
		} else if (choice == 3) {
			straight();
		}
	}
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
	public void straight() {
		if (timer.get() < 5) {
			drive.arcadeDrive(0, .6);
		} else
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
