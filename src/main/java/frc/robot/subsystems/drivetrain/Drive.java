package frc.robot.subsystems.drivetrain;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveSettings;

public class Drive extends SubsystemBase implements DriveSettings {

	private final CANSparkMax leftMaster = new CANSparkMax(LEFT_MASTER, MotorType.kBrushless);
	private final CANSparkMax leftFollow = new CANSparkMax(LEFT_FOLLOW, MotorType.kBrushless);
	private final CANSparkMax rightMaster = new CANSparkMax(RIGHT_MASTER, MotorType.kBrushless);
	private final CANSparkMax rightFollow = new CANSparkMax(RIGHT_FOLLOW, MotorType.kBrushless);
	private final DifferentialDrive differentialDrive = new DifferentialDrive(leftMaster, rightMaster);

	private final AHRS ahrs = new AHRS();

	private Field2d field = new Field2d();
	private final double EPSILON = 0.1;
	// private DifferentialDrivetrainSim diffDriveSim = new
	// DifferentialDrivetrainSim(
	// LinearSystemId.identifyDrivetrainSystem(
	// kV,
	// kA,
	// kVAngular,
	// kAAngular,
	// TRACK_WIDTH),
	// DCMotor.getNeo550(4),
	// GEAR_REDUCTION,
	// TRACK_WIDTH,
	// WHEEL_DIAMETER / 2,
	// VecBuilder.fill(0, 0, 0, 0, 0, 0, 0));

	public Drive() {
		configureMotors();
		SmartDashboard.putData(field);
		// differentialDrive.setExpiration(10);
	}

	public double getAngle() {
		return ahrs.getPitch() - 2.4;
	}

	private void configureMotors() {
		rightFollow.follow(rightMaster);
		leftFollow.follow(leftMaster);

		leftMaster.setIdleMode(IdleMode.kBrake);
		rightMaster.setIdleMode(IdleMode.kBrake);
		leftFollow.setIdleMode(IdleMode.kBrake);
		rightFollow.setIdleMode(IdleMode.kBrake);

		leftMaster.setSmartCurrentLimit(60);
		leftFollow.setSmartCurrentLimit(60);
		rightMaster.setSmartCurrentLimit(60);
		rightFollow.setSmartCurrentLimit(60);

		leftMaster.burnFlash();
		rightMaster.burnFlash();
		leftFollow.burnFlash();
		rightFollow.burnFlash();
	}

	public void arcadeDrive(double rotation, double speed) {
		// differentialDrive.feed();
		differentialDrive.arcadeDrive(rotation, speed);
	}
	public DifferentialDrive getDiffDrive() {
		return differentialDrive;
	}
	// public void balanceBot() {

	// while(ahrs.getPitch() - EPSILON >= 0 || ahrs.getPitch() + EPSILON <= 0) {
	// System.out.println("Pitch: " + ahrs.getPitch());
	// System.out.println("Roll: " + ahrs.getRoll());
	// System.out.println("Yaw: " + ahrs.getYaw());
	// if(ahrs.getPitch() - EPSILON >= 0) {
	// arcadeDrive(0, .6);
	// } else if(ahrs.getPitch() + EPSILON >= 0) {
	// arcadeDrive(0, -0.6);
	// } else {
	// arcadeDrive(0, 0);
	// }
	// }
	// }

	public void periodic() {
		// field.setRobotPose(diffDriveSim.getPose());
	}

	@Override
	public void simulationPeriodic() {
		// diffDriveSim.update(0.02);
	}

}
