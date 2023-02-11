package frc.robot.subsystems.drivetrain;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.REVPhysicsSim;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveSettings;

public class Drive extends SubsystemBase implements DriveSettings {

	protected final CANSparkMax leftMaster = new CANSparkMax(LEFT_MASTER, MotorType.kBrushless);
	protected final CANSparkMax leftSlave = new CANSparkMax(LEFT_SLAVE, MotorType.kBrushless);
	protected final CANSparkMax rightMaster = new CANSparkMax(RIGHT_MASTER, MotorType.kBrushless);
	protected final CANSparkMax rightSlave = new CANSparkMax(RIGHT_SLAVE, MotorType.kBrushless);
	protected final DifferentialDrive differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
	//protected final SparkMaxAlternateEncoder leftEncoder = new SparkMaxAlternateEncoder(leftMaster,MotorType.kBrushless, 4096);
	//leftEncoder = leftMaster.getEncoder();
	//rightEncoder = rightMaster.getEncoder();
	private final AHRS ahrs = new AHRS();
	
	//protected EncoderSim leftEncoderSim = new EncoderSim();
	;
	protected Field2d field = new Field2d();
	protected DifferentialDrivetrainSim diffDriveSim = new DifferentialDrivetrainSim(
			LinearSystemId.identifyDrivetrainSystem(
					kV,
					kA,
					kVAngular,
					kAAngular,
					TRACK_WIDTH),
			DCMotor.getNeo550(4),
			GEAR_REDUCTION,
			TRACK_WIDTH,
			WHEEL_DIAMETER / 2,
			VecBuilder.fill(0.001, 0.001, 0.001, 0.1, 0.1, 0.005, 0.005));

	public Drive() {
		configureMotors();
		SmartDashboard.putData(field);
	}

	public double getAngle() {
		return ahrs.getAngle();
	}

	private void configureMotors() {
		rightSlave.follow(rightMaster);
		leftSlave.follow(leftMaster);

		leftMaster.setIdleMode(IdleMode.kBrake);
		rightMaster.setIdleMode(IdleMode.kBrake);
		leftSlave.setIdleMode(IdleMode.kBrake);
		rightSlave.setIdleMode(IdleMode.kBrake);
		

		//stuff for sim?
		// leftEncoder.setPositionConversionFactor(WHEEL_CIRCUMFERENCE / GEAR_RATIO );
    	// rightEncoder.setPositionConversionFactor(WHEEL_CIRCUMFERENCE / GEAR_RATIO);

    	// leftEncoder.setVelocityConversionFactor(WHEEL_CIRCUMFERENCE * (1.0/60.0) / GEAR_RATIO);
    	// rightEncoder.setVelocityConversionFactor(WHEEL_CIRCUMFERENCE * (1.0/60.0) / GEAR_RATIO);
		//Encoder leftEncoder = new Encoder(0, 1);
		//leftEncoder.setDistancePerPulse(2 * Math.PI * kWheelRadius / kEncoderResolution);

	}

	public void arcadeDrive(double speed, double rotation) {
		differentialDrive.arcadeDrive(speed, rotation);
	}

	public void balanceBot() {
		if (getAngle() >= 0.5) {
			arcadeDrive(.6, 0);
		} else if (getAngle() <= 0.5) {
			arcadeDrive(-.6, 0);
		} else {
			arcadeDrive(0, 0);
		}
	}

	public void periodic() {
		field.setRobotPose(diffDriveSim.getPose());
	}

	@Override
	public void simulationPeriodic() {
		
 	 	//rightEncoder.setDistancePerPulse(2 * Math.PI * kWheelRadius / kEncoderResolution);
		diffDriveSim.setInputs(leftMaster.get() * RobotController.getInputVoltage(), rightMaster.get() * RobotController.getInputVoltage());
		diffDriveSim.update(0.02);
		System.out.println(leftMaster.getAppliedOutput()); 
		

		// leftEncoderSim.setDistance(diffDriveSim.getLeftPositionMeters());
  		// leftEncoderSim.setRate(diffDriveSim.getLeftVelocityMetersPerSecond());
  		// rightEncoderSim.setDistance(diffDriveSim.getRightPositionMeters());
  		// rightEncoderSim.setRate(diffDriveSim.getRightVelocityMetersPerSecond());
  		// gyroSim.setAngle(-diffDriveSim.getHeading().getDegrees());

		
	}

	public CANSparkMax getLeftMaster() {
		return leftMaster;
	}

	public CANSparkMax getRightMaster() {
		return rightMaster;
	}
}
