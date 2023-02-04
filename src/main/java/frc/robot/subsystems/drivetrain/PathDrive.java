package frc.robot.subsystems.drivetrain;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;

import static frc.robot.Constants.*;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.RelativeEncoder;

public class PathDrive extends Drive{
	Pose2d pose;
	private final RelativeEncoder leftMasterEncoder = leftMaster.getEncoder();
	private final RelativeEncoder rightMasterEncoder = rightMaster.getEncoder();
	//the odometry tracks the robot's pose
	private final DifferentialDriveOdometry odometry;
	//gyroscope measures the change in the robot's heading
	private final AHRS navX = new AHRS();

	public PathDrive() {
		leftMasterEncoder.setPositionConversionFactor(kEncoderDistancePerTick);
		rightMasterEncoder.setPositionConversionFactor(kEncoderDistancePerTick);

		resetEncoders();
		odometry = new DifferentialDriveOdometry(navX.getRotation2d(), leftMasterEncoder.getPosition(), rightMasterEncoder.getPosition());
	}

	SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(kS, kV, kA);

	//fill in with kp, ki, and kd values later
	PIDController leftPidController = new PIDController(0.01831, 0, .001);
	PIDController rightPidController = new PIDController(0.01831, 0, .001);

	public SimpleMotorFeedforward getFeedforward() {
		return feedforward;
	}


	@Override
	public void periodic() {
		// Update the odometry in the periodic block for every main loop iteration
		odometry.update(navX.getRotation2d(), leftMasterEncoder.getPosition(), rightMasterEncoder.getPosition());
	}

	//returns current pose
	public Pose2d getPose() {
		return odometry.getPoseMeters();
	}

	//returns current wheel speeds
	public DifferentialDriveWheelSpeeds getWheelSpeeds() {
		return new DifferentialDriveWheelSpeeds(leftMasterEncoder.getVelocity(), rightMasterEncoder.getVelocity());
	}

	//returns current heading
	public double getHeading() {
		return navX.getRotation2d().getDegrees();
	}

	//resets odometry to specified pose
	public void resetOdometry(Pose2d pose) {
		resetEncoders();
		odometry.resetPosition(navX.getRotation2d(), leftMasterEncoder.getPosition(), rightMasterEncoder.getPosition(), pose);
	}

	//controls motors through setting voltages
	// May need to adjust and add a scaling factor due to robot bend
	public void tankDriveVolts(double leftVolts, double rightVolts) {
		leftMaster.setVoltage(leftVolts);
		rightMaster.setVoltage(rightVolts);
		differentialDrive.feed();
	}

	//resets drive encoders to read a position of 0
	public void resetEncoders() {
		leftMasterEncoder.setPosition(0);
		rightMasterEncoder.setPosition(0);
	}

	//returns average of 2 encoder readings
	public double getAverageEncoderDistance() {
		return (leftMasterEncoder.getPosition() + rightMasterEncoder.getPosition()) / 2.0;
	}

	public RelativeEncoder getLeftEncoder() {
		return leftMasterEncoder;
	}

	public RelativeEncoder getRightEncoder() {
		return rightMasterEncoder;
	}

	//sets the max output of the drive, used for scaling speeds
	public void setMaxOutput(double maxOutput) {
		differentialDrive.setMaxOutput(maxOutput);
	}

	//resets heading to 0
	public void zeroHeading() {
		navX.reset();
	}

	//returns robot's current turn rate
	public double getTurnRate() {
		return -navX.getRate();
	}

	public PIDController getLeftPidController() {
		return leftPidController;
	}

	public PIDController getRightPidController() {
		return rightPidController;
	}
}
