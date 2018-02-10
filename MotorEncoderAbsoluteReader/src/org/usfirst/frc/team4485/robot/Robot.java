/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4485.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team4485.robot.Subsystems.PIDController.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class. The
 * SampleRobot class is the base of a robot application that will automatically
 * call your Autonomous and OperatorControl methods at the right time as
 * controlled by the switches on the driver station or the field controls.
 *
 * <p>The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 *
 * <p>WARNING: While it may look like a good choice to use for your code if
 * you're inexperienced, don't. Unless you know what you are doing, complex code
 * will be much more difficult under this system. Use IterativeRobot or
 * Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
	
	WPI_TalonSRX talon;
	PIDController pidController;
	SPID spid;
	
	public Robot() {
		//Constructor for the robot
	}

	@Override
	public void robotInit() {
		//Initialize robot stuff here
		talon = new WPI_TalonSRX(1); //(int) is the port Device Id
		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10); //Sets feedback sensor to (FeedbackDevice, ?, timeout_ms)
		pidController = new PIDController();
		spid = new SPID();
		spid.dGain = 0;
		spid.dState = 0;
		spid.iGain = 0;
		spid.iMax = 0;
		spid.iMin = 0;
		spid.iState = 0;
		spid.pGain = 0;
		pidController.setSPID(spid);
		
		SmartDashboard.putNumber("PID P", spid.pGain);
		SmartDashboard.putNumber("PID I", spid.iGain);
		SmartDashboard.putNumber("PID D", spid.dGain);
	}

	@Override
	public void autonomous() {
		//Auto goes here
	}

	
	@Override
	public void operatorControl() {
		double velocity=0;
		double position=0;
		double targetPosition = 420;
		
		while (isOperatorControl() && isEnabled()) {


			velocity = talon.getSelectedSensorVelocity(0); // Gets the speed of the motor, stores it in velocity
			position = talon.getSelectedSensorPosition(0); // Gets the position of the motor, stores it in position
			
			
			SmartDashboard.putNumber("Encoder Velocity", velocity); //Shows velocity on the SmartDashboard
			SmartDashboard.putNumber("Encoder Position", position); //Shows position on the SmartDashboard

			spid.pGain = SmartDashboard.getNumber("PID P", 0);
			spid.iGain = SmartDashboard.getNumber("PID I", 0);
			spid.dGain = SmartDashboard.getNumber("PID D", 0);
			pidController.setSPID(spid);
			
			SmartDashboard.putNumber("PID P", spid.pGain);
			SmartDashboard.putNumber("PID I", spid.iGain);
			SmartDashboard.putNumber("PID D", spid.dGain);
			
			
			double moveAmount = pidController.update(targetPosition-position, position);
			
			SmartDashboard.putNumber("Move Amount", moveAmount);
			
			if(moveAmount > 1) {
				moveAmount = 1;
			} else if (moveAmount < -1) {
				moveAmount = -1;
			}
			
			talon.set(moveAmount);
			
			
			Timer.delay(0.001); // (time in ms)
		}
	}
}
