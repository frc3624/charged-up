// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drive;
import frc.robot.subsystems.dumpy.Dumper;

public class AutoJank extends CommandBase {
	private Gyro gyro;
	private Drive drive;
	private Timer timer = new Timer();
	private Dumper dump;
	private static final String timedBalance = "Timed Balance";
	private static final String straight = "Straight";
	private static final String autoBalance = "Auto Balance";
	private final SendableChooser<String> choice = new SendableChooser<>();

	// private final ComplexWidget leftMid = tab.add("leftMiddle", new
	// AutoLeftMiddle(drive));
	// private SendableChooser<CommandBase> auto = new
	// SendableChooser<CommandBase>();
	// private final ShuffleboardTab tab = Shuffleboard.getTab("auto");
	// private GenericEntry leftMid = tab.add("Left Middle Routine",
	// false).getEntry();
	// private GenericEntry straight = tab.add("Straight", false).getEntry();

	/** Creates a new AutoJank. */
	public AutoJank(Drive drive, Dumper dump) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.drive = drive;
		this.dump = dump;
		// this.gyro = gyro;
		// addRequirements(gyro);
		addRequirements(dump);
		addRequirements(drive);
		timer.start();
		choice.setDefaultOption("Auto Balance", autoBalance);
		choice.addOption("Straight", straight);
		choice.addOption("Timed Balance", timedBalance);
		SmartDashboard.putData("Auto Choices", choice);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		// System.out.println(choice);
		// drive.arcadeDrive(0, .65);
		dump.dump();
		Timer.delay(1);
		dump.dump();
		// while(timer.get() < 6){
		// drive.arcadeDrive(0, .4);
		// }
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		timedBalance();
		// System.out.println(gyro.getAngle() + " " + timer.get() );
		// drive.arcadeDrive(0, 0);
		// drive.arcadeDrive(0, 0);
		// System.out.println(gyro.getAngle());
		// if(timer.get() < 2){
		// drive.arcadeDrive(0, 1);
		// }
		// if(gyro.getAngle() > 2)
		// drive.arcadeDrive(0, -.2);
		// if(gyro.getAngle() < -2)
		// drive.arcadeDrive(0, .2);
		// else
		// drive.arcadeDrive(0, 0);
	}
	public void decisionTree() {
		String currentRoutine = choice.getSelected();
		if (currentRoutine.equals("Timed Balance")) {
			timedBalance();
		} else if (currentRoutine.equals("Straight")) {
			straight();
		} else if (currentRoutine.equals("Auto Balance")) {
			autoBalance();
		}
	}
	public void autoBalance() {
		if (timer.get() < 2)
			drive.arcadeDrive(0, 0);
		else if (timer.get() < 6.3)
			drive.arcadeDrive(0, -.75);
		else if (timer.get() < 8.4)
			drive.arcadeDrive(0, .75);
		// else if (gyro.getAngle() > 2.5)
		// drive.arcadeDrive(0, -.3);
		// else if (gyro.getAngle() < -2.5)
		// drive.arcadeDrive(0, .3);
		else
			drive.arcadeDrive(0, 0);
	}
	public void timedBalance() {
		if (timer.get() < 3)
			drive.arcadeDrive(0, 0);
		else if (timer.get() < 9)
			drive.arcadeDrive(0, -.65);
		else if (timer.get() < 12.75)
			drive.arcadeDrive(0, .65);
		else
			drive.arcadeDrive(0, 0);
	}
	public void straight() {
		if (timer.get() < 2)
			drive.arcadeDrive(0, 0);
		else if (timer.get() < 9) {
			drive.arcadeDrive(0, -.6);// was .6
		} else
			drive.arcadeDrive(0, 0);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		drive.arcadeDrive(0, 0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
