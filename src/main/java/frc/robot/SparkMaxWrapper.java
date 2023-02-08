package frc.robot;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.hal.SimDevice;
import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.SimDeviceSim;

public class SparkMaxWrapper extends CANSparkMax{
    private SimDouble simSpeed;
    private SimDevice simSparkMax;
    public SparkMaxWrapper(int deviceID, MotorType type) {
        super(deviceID,type);
        simSparkMax = SimDevice.create("SparkMax",deviceID);
    }
    @Override
    public double get(){
        if (simSparkMax != null){
            return simSpeed.get();
        }
        return super.get();
    }

    @Override
    public void set(double speed){
        if (simSparkMax != null){
            simSpeed.set(speed);
        }else{
            super.set(speed);
        }
    }

    @Override
    public void setVoltage(double outputVolts) { //For simulation purposes, we are expecting that the battery voltage stays constant.
        if (simSparkMax != null){
            set(outputVolts / RobotController.getBatteryVoltage());
        } else {
            super.setVoltage(outputVolts);
        }
    }
}
