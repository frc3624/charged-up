// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableListener;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.NetworkButton;
import frc.robot.subsystems.drivetrain.Drive;
import frc.robot.subsystems.dumpy.Dumper;
import io.github.oblarg.oblog.annotations.Config;

public class AutoJank extends CommandBase {
	private Drive drive;
	private Timer timer = new Timer();
	private Dumper dump;
	 private NetworkTableInstance instance = NetworkTableInstance.getDefault();
	//private NetworkTable table = instance.getTable("auto");
	// private final ShuffleboardTab tab = Shuffleboard.getTab("auto");

	//private final ComplexWidget leftMid = tab.add("leftMiddle", new AutoLeftMiddle(drive));
	//private SendableChooser<CommandBase> auto = new SendableChooser<CommandBase>();
	private final ShuffleboardTab tab = Shuffleboard.getTab("auto");
	private GenericEntry leftMid = tab.add("Left Middle Routine", false).getEntry();
	/** Creates a new AutoJank. */
	public AutoJank(Drive drive, Dumper dump) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.drive = drive;
		this.dump = dump;
		addRequirements(dump);
		addRequirements(drive);
		timer.start();
		//auto.addOption("leftMid", new AutoJank(drive, dump));
		//SmartDashboard.putData("Auto Modes",auto);
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
		decisionTree();
	}
	//@Config.ToggleButton

	public void decisionTree(){
		if()
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
