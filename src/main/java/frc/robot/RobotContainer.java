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
import frc.robot.commands.Autos;
import frc.robot.commands.ChangeServo;
import frc.robot.commands.DriveTrain;
import frc.robot.commands.LevelChargingStation;
import frc.robot.commands.ShiftDump;
import frc.robot.commands.ShiftTrap;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Dumper;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Trap;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
	// Controller Stuff
	protected static final CommandXboxController xboxController = new CommandXboxController(
			Constants.kDriverControllerPort);
	private final Trigger dumpButton = xboxController.y();
	private final Trigger trapButton = xboxController.x();
	private final Trigger servoButton = xboxController.leftStick();
	// I/O + Vision
	UsbCamera rearCam = CameraServer.startAutomaticCapture();
	CvSink cvSink = CameraServer.getVideo();
	CvSource outputStream = CameraServer.putVideo("Rear Cam", 680, 480);

	// Subsystems
	private final Drive drive = new Drive();
	private final Dumper dump = new Dumper();
	private final Trap trap = new Trap();
	private final Limelight limelight = new Limelight();
	// Commands
	private final DriveTrain driveTrain = new DriveTrain(drive, xboxController);
	private final LevelChargingStation levelChargingStation = new LevelChargingStation(drive);
	private final ShiftDump dumper = new ShiftDump(dump);
	private final ShiftTrap trapper = new ShiftTrap(trap);
	private final ChangeServo servo = new ChangeServo(limelight, xboxController);

	// Replace with CommandPS4Controller or CommandJoystick if needed

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Configure the trigger bindings
		configureBindings();
		configureButtonBindings();
		drive.setDefaultCommand(driveTrain);
	}

	/**
	 * Use this method to define your trigger->command mappings. Triggers can be
	 * created via the {@link Trigger#Trigger(java.util.function.BooleanSupplier)}
	 * constructor with an arbitrary predicate, or via the named factories in
	 * {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses
	 * for {@link CommandXboxController
	 * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
	 * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick
	 * Flight joysticks}.
	 */
	private void configureBindings() {
		// Schedule `ExampleCommand` when `exampleCondition` changes to `true`
		// new Trigger(drive::)
		// .onTrue(new ChaseTarget(drive));

		// Schedule `exampleMethodCommand` when the Xbox controller's B button is
		// pressed,
		// cancelling on release.
		// m_driverController.b().whileTrue(drive.autoSequenceShutUp());

	}
	private void configureButtonBindings() {
		servoButton.whileTrue(servo);
		dumpButton.whileTrue(dumper);
		trapButton.whileTrue(trapper);
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
