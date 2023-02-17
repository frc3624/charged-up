package frc.robot.subsystems.drivetrain;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.REVPhysicsSim;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.hal.SimDevice;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.simulation.SimDeviceSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveSettings;

public class Drive extends SubsystemBase implements DriveSettings {
	//real stuff
	protected final CANSparkMax leftMaster = new CANSparkMax(LEFT_MASTER, MotorType.kBrushless);
	protected final CANSparkMax leftFollow = new CANSparkMax(LEFT_FOLLOW, MotorType.kBrushless);
	protected final CANSparkMax rightMaster = new CANSparkMax(RIGHT_MASTER, MotorType.kBrushless);
	protected final CANSparkMax rightFollow = new CANSparkMax(RIGHT_FOLLOW, MotorType.kBrushless);
	protected final DifferentialDrive differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
	//protected final SparkMaxAlternateEncoder leftEncoder = new SparkMaxAlternateEncoder(leftMaster,MotorType.kBrushless, 4096);
	RelativeEncoder leftEncoderActual = leftMaster.getEncoder();
	RelativeEncoder rightEncoderActual = rightMaster.getEncoder();
	private final AHRS ahrs = new AHRS();
	//CHANGE BEFORE EVERY MATCH
	Pose2d position = new Pose2d(BLUE_1X, PCM_ID, new Rotation2d());
	/*CHANGE WHEN SOLUTION IS FOUND TO INDICATED PROBLEMS */
	DifferentialDriveOdometry odometryActual = new DifferentialDriveOdometry
										(/*gyro angle*/ahrs.getRotation2d(), 
										/*left encoder position(should be distance but it'll most likely make no difference)*/
										leftEncoderActual.getPosition() * leftEncoderActual.getPositionConversionFactor(),
										/*right encoder position(should be distance but it'll most likely make no difference)*/ 
										rightEncoderActual.getPosition() * rightEncoderActual.getPositionConversionFactor(),
										position);
	
	//fake stuff
	
	// private Encoder leftEncoder = new Encoder(LEFT_MASTER, LEFT_FOLLOW);
	// private Encoder rightEncoder = new Encoder(RIGHT_MASTER,RIGHT_FOLLOW);
	// private EncoderSim leftEncoderSim = new EncoderSim(leftEncoder);
	// private EncoderSim rightEncoderSim = new EncoderSim(rightEncoder);
	// SimDeviceSim gyroPsuedo = new SimDeviceSim("gyro",8);
	// AnalogGyro gyro = new AnalogGyro(gyroPsuedo.getNativeHandle());
	// AnalogGyroSim gyroSim = new AnalogGyroSim(gyro);

	// DifferentialDriveOdometry odometryFake = new DifferentialDriveOdometry
	// 									(ahrs.getRotation2d(), 
	// 									leftEncoder.getDistance(),
	// 									rightEncoder.getDistance(),
	// 									position);
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
		rightFollow.follow(rightMaster);
		leftFollow.follow(leftMaster);

		leftMaster.setIdleMode(IdleMode.kBrake);
		rightMaster.setIdleMode(IdleMode.kBrake);
		leftFollow.setIdleMode(IdleMode.kBrake);
		rightFollow.setIdleMode(IdleMode.kBrake);
		

		//stuff for sim?
		// leftEncoder.setPositionConversionFactor(WHEEL_DIAMETER * Math.PI / GEAR_REDUCTION );
		// rightEncoder.setPositionConversionFactor(WHEEL_DIAMETER * Math.PI / GEAR_REDUCTION );

		// leftEncoder.setVelocityConversionFactor(WHEEL_DIAMETER * Math.PI / GEAR_REDUCTION * (1.0/60.0));
		// rightEncoder.setVelocityConversionFactor(WHEEL_DIAMETER * Math.PI / GEAR_REDUCTION * (1.0/60.0));


		//Encoder leftEncoder = new Encoder(0, 1);
		// leftEncoder.setDistancePerPulse(Math.PI * WHEEL_DIAMETER/ 42);
		// rightEncoder.setDistancePerPulse(Math.PI * WHEEL_DIAMETER/ 42);

	}

	public void arcadeDrive(double speed, double rotation) {
		differentialDrive.arcadeDrive(speed, rotation);
	}

	public void balanceBot() {
		if (getAngle() >= 2) {
			arcadeDrive(.3, 0);
		} else if (getAngle() <= -2) {
			arcadeDrive(-.3, 0);
		} else {
			arcadeDrive(0, 0);
		}
	}

	public void periodic() {
		field.setRobotPose(diffDriveSim.getPose());
		odometryActual.update(ahrs.getRotation2d(),
							leftEncoderActual.getPosition() * leftEncoderActual.getPositionConversionFactor(),
							rightEncoderActual.getPosition() * rightEncoderActual.getPositionConversionFactor());

		// odometryFake.update(gyro.getRotation2d(),
		// 				leftEncoder.getDistance(),
		// 				rightEncoder.getDistance());
		// field.setRobotPose(odometryFake.getPoseMeters());
	}

	@Override
	public void simulationPeriodic() {
		
 	 	//rightEncoder.setDistancePerPulse(2 * Math.PI * kWheelRadius / kEncoderResolution);
		diffDriveSim.setInputs(leftMaster.get() * RobotController.getInputVoltage(), rightMaster.get() * RobotController.getInputVoltage());
		diffDriveSim.update(0.02);
		//System.out.println(leftMaster.getAppliedOutput()); 
		

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
