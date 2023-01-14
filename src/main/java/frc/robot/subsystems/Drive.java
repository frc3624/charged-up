package frc.robot.subsystems;

import static frc.robot.Constants.LEFT_MASTER_ID;
import static frc.robot.Constants.LEFT_SLAVE_ID;
import static frc.robot.Constants.RIGHT_MASTER_ID;
import static frc.robot.Constants.RIGHT_SLAVE_ID;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * This is a temporary drive subsystem. This will be updated once the robot
 * frame is constructed and the drive train is installed.
 */

public class Drive extends SubsystemBase {

	private final WPI_TalonSRX leftMaster = new WPI_TalonSRX(LEFT_MASTER_ID);
	private final WPI_TalonSRX leftSlave = new WPI_TalonSRX(LEFT_SLAVE_ID);
	private final WPI_TalonSRX rightMaster = new WPI_TalonSRX(RIGHT_MASTER_ID);
	private final WPI_TalonSRX rightSlave = new WPI_TalonSRX(RIGHT_SLAVE_ID);

	private final DifferentialDrive differentialDrive = new DifferentialDrive(leftMaster, rightMaster);

	public Drive() {
		leftSlave.set(ControlMode.Follower, LEFT_MASTER_ID);
		rightSlave.set(ControlMode.Follower, RIGHT_MASTER_ID);
	}

	public void arcadeDrive(double speed, double rotation) {
		differentialDrive.arcadeDrive(speed, rotation);
	}

	public boolean autoSequenceShutUp() {
		return false;
	}
}
