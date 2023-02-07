// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.GlobalSettings;
import frc.robot.commands.Autos;
import frc.robot.commands.drive.DriveTrain;
import frc.robot.commands.drive.LevelChargingStation;
import frc.robot.commands.dumpy.ShiftDump;
import frc.robot.commands.dumpy.ShiftTrap;
import frc.robot.commands.limelight.DrivePosition;
import frc.robot.commands.limelight.IntakePosition;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.drivetrain.Drive;
import frc.robot.subsystems.dumpy.Dumper;
import frc.robot.subsystems.dumpy.Trap;

public class RobotContainer implements GlobalSettings {
	// I/O + Vision
	protected static final CommandXboxController xboxController = new CommandXboxController(XBOX_ID);
	private final Trigger dumpButton = xboxController.y();
	private final Trigger trapButton = xboxController.x();
	private final Trigger balanceButton = xboxController.a();
	private final Trigger intakeViewButton = xboxController.povDown();
	private final Trigger driveViewButton = xboxController.povUp();

	UsbCamera rearCam = CameraServer.startAutomaticCapture();
	CvSink cvSink = CameraServer.getVideo();
	CvSource outputStream = CameraServer.putVideo("Rear Cam", 680, 480);

	// Single instance of Compressor, easy access

	// Subsystems
	private final Drive drive = new Drive();
	private final Dumper dump = new Dumper();
	private final Trap trap = new Trap();
	private final Limelight limelight = new Limelight();

	// Commands
	private final DriveTrain driveTrain = new DriveTrain(drive, xboxController);
	private final LevelChargingStation leveler = new LevelChargingStation(drive);
	private final ShiftDump dumper = new ShiftDump(dump);
	private final ShiftTrap trapper = new ShiftTrap(trap);
	private final DrivePosition drivePosition = new DrivePosition(limelight);
	private final IntakePosition intakePosition = new IntakePosition(limelight);

	// Replace with CommandPS4Controller or CommandJoystick if needed

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Configure the trigger bindings
		configureButtonBindings();
		drive.setDefaultCommand(driveTrain);
		xboxController.start();
	}

	private void configureButtonBindings() {
		dumpButton.whileTrue(dumper);
		trapButton.whileTrue(trapper);
		intakeViewButton.whileTrue(intakePosition);
		driveViewButton.whileTrue(drivePosition);
		balanceButton.whileTrue(leveler);
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// An example command will be run in autonomous
		return Autos.exampleAuto(drive);
	}
}
