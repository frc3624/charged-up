package frc.robot.subsystems.drivetrain;

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
	private final CANSparkMax leftSlave = new CANSparkMax(LEFT_SLAVE, MotorType.kBrushless);
	private final CANSparkMax rightMaster = new CANSparkMax(RIGHT_MASTER, MotorType.kBrushless);
	private final CANSparkMax rightSlave = new CANSparkMax(RIGHT_SLAVE, MotorType.kBrushless);
	private final DifferentialDrive differentialDrive = new DifferentialDrive(leftMaster, rightMaster);

	private Field2d field = new Field2d();
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
	}

	private void configureMotors() {
		rightSlave.follow(rightMaster);
		leftSlave.follow(leftMaster);

		leftMaster.setIdleMode(IdleMode.kCoast);
		rightMaster.setIdleMode(IdleMode.kCoast);
		leftSlave.setIdleMode(IdleMode.kCoast);
		rightSlave.setIdleMode(IdleMode.kCoast);
	}

	public void arcadeDrive(double speed, double rotation) {
		differentialDrive.arcadeDrive(speed, rotation);
	}

	public void periodic() {
		// field.setRobotPose(diffDriveSim.getPose());
	}

	@Override
	public void simulationPeriodic() {
		// diffDriveSim.update(0.02);
	}

}
