/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4485.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
	
	TalonSRX talon;
	
	public Robot() {
		//Constructor for the robot
	}

	@Override
	public void robotInit() {
		//Initialize robot stuff here
		talon = new TalonSRX(1);
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
	}

	@Override
	public void autonomous() {
		//Auto goes here
		
	}

	
	@Override
	public void operatorControl() {
		double velocity;
		int position;
		
		while (isOperatorControl() && isEnabled()) {

			velocity = talon.getSelectedSensorVelocity(0);
			position = talon.getSelectedSensorPosition(0);
			SmartDashboard.putNumber("Encoder Velocity", velocity);
			SmartDashboard.putNumber("Encoder Position", position);
			
			// The motors will be updated every 5ms
			Timer.delay(0.005);
		}
	}

	/**
	 * Runs during test mode.
	 */
	@Override
	public void test() {
	}
}