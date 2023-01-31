package frc.robot.subsystems;

import static frc.robot.Constants.LEFT_MASTER;
import static frc.robot.Constants.LEFT_SLAVE;
import static frc.robot.Constants.RIGHT_MASTER;
import static frc.robot.Constants.RIGHT_SLAVE;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * This is a temporary drive subsystem. This will be updated once the robot
 * frame is constructed and the drive train is installed.
 */

public class Drive extends SubsystemBase {

	private final CANSparkMax leftMaster = new CANSparkMax(LEFT_MASTER, MotorType.kBrushless);
	private final CANSparkMax leftSlave = new CANSparkMax(LEFT_SLAVE, MotorType.kBrushless);
	private final CANSparkMax rightMaster = new CANSparkMax(RIGHT_MASTER, MotorType.kBrushless);
	private final CANSparkMax rightSlave = new CANSparkMax(RIGHT_SLAVE, MotorType.kBrushless);
	private final DifferentialDrive differentialDrive = new DifferentialDrive(leftMaster, rightMaster);

	public Drive() {
		rightSlave.follow(rightMaster);
		leftSlave.follow(leftMaster);

		leftMaster.setIdleMode(IdleMode.kBrake);
		rightMaster.setIdleMode(IdleMode.kBrake);
		leftSlave.setIdleMode(IdleMode.kBrake);
		rightSlave.setIdleMode(IdleMode.kBrake);
	}

	public void arcadeDrive(double speed, double rotation) {
		differentialDrive.arcadeDrive(speed, rotation);
	}

	public boolean autoSequenceShutUp() {
		return false;
	}
}
