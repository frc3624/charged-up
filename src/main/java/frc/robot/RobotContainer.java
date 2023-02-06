// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.XBOX_ID;

import java.util.List;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.drive.DriveTrain;
import frc.robot.commands.drive.LevelChargingStation;
import frc.robot.commands.dumpy.ShiftDump;
import frc.robot.commands.dumpy.ShiftTrap;
import frc.robot.commands.limelight.DrivePosition;
import frc.robot.commands.limelight.IntakePosition;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.drivetrain.PathDrive;
import frc.robot.subsystems.dumpy.Dumper;
import frc.robot.subsystems.dumpy.Trap;

public class RobotContainer {
	// I/O + Vision
	protected static final CommandXboxController xboxController = new CommandXboxController(XBOX_ID);
	private final Trigger dumpButton = xboxController.y();
	private final Trigger trapButton = xboxController.x();
	private final Trigger intakeViewButton = xboxController.povDown();
	private final Trigger driveViewButton = xboxController.povUp();

	// fix later
	public final double kS = 0.083938, kV = 3.2001, kA = 0.23026, kSAngular = 0.09612, kVAngular = 3.0182,
			kAAngular = 0.27358;
	public final double TRACK_WIDTH = Units.inchesToMeters(18.75);
	public final double GEAR_REDUCTION = 14.17;
	public final double WHEEL_DIAMETER = Units.inchesToMeters(6);

	UsbCamera rearCam = CameraServer.startAutomaticCapture();
	CvSink cvSink = CameraServer.getVideo();
	CvSource outputStream = CameraServer.putVideo("Rear Cam", 680, 480);

	// Single instance of Compressor, easy access

	// Subsystems
	// private final Drive drive = new Drive();
	private final PathDrive pathDrive = new PathDrive();
	private final Dumper dump = new Dumper();
	private final Trap trap = new Trap();
	private final Limelight limelight = new Limelight();

	// Commands
	private final DriveTrain driveTrain = new DriveTrain(pathDrive, xboxController);
	private final LevelChargingStation leveler = new LevelChargingStation(pathDrive);
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
		pathDrive.setDefaultCommand(driveTrain);
	}

	private void configureButtonBindings() {
		dumpButton.whileTrue(dumper);
		trapButton.whileTrue(trapper);
		intakeViewButton.whileTrue(intakePosition);
		driveViewButton.whileTrue(drivePosition);
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// Create a voltage constraint to ensure we don't accelerate too fast
		var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
				new SimpleMotorFeedforward(
						kS,
						kV,
						kA),
				Constants.kDriveKinematics,
				10);

		// Create config for trajectory
		TrajectoryConfig config = new TrajectoryConfig(
				Constants.kMaxSpeed,
				Constants.kMaxAcceleration)
				// Add kinematics to ensure max speed is actually obeyed
				.setKinematics(Constants.kDriveKinematics)
				// Apply the voltage constraint
				.addConstraint(autoVoltageConstraint);

		// An example trajectory to follow. All units in meters.
		Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
				// Start at the origin facing the +X direction
				new Pose2d(0, 0, new Rotation2d(0)),
				// Pass through these two interior waypoints, making an 's' curve path
				List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
				// End 3 meters straight ahead of where we started, facing forward
				new Pose2d(3, 0, new Rotation2d(0)),
				// Pass config
				config);

		RamseteCommand ramseteCommand = new RamseteCommand(
				exampleTrajectory,
				pathDrive::getPose,
				new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
				new SimpleMotorFeedforward(
						kS,
						kV,
						kA),
				Constants.kDriveKinematics,
				pathDrive::getWheelSpeeds,
				new PIDController(Constants.kPDriveVel, 0, 0),
				new PIDController(Constants.kPDriveVel, 0, 0),
				// RamseteCommand passes volts to the callback
				pathDrive::tankDriveVolts,
				pathDrive);

		// Reset odometry to the starting pose of the trajectory.
		pathDrive.resetOdometry(exampleTrajectory.getInitialPose());

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> pathDrive.tankDriveVolts(0, 0));
	}
}
