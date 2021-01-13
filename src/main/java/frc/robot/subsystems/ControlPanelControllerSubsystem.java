/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ControlPanelControllerConstants;

public class ControlPanelControllerSubsystem extends SubsystemBase {

	private final TalonSRX m_Motor = new TalonSRX(ControlPanelControllerConstants.kMotorPort);

	private NetworkTableEntry motorVelocity = Shuffleboard.getTab("Control Panel")
		.add("Controller", m_Motor.getSelectedSensorVelocity())
		.withWidget(BuiltInWidgets.kTextView)
		.getEntry(); 

	private ColorSensorSubsystem m_colorSensor;

	/*private NetworkTableEntry selectTheColor = Shuffleboard.getTab("Control Panel")
		.add("Which Color", m_colorSensor.getColorSelector())
		.withWidget(BuiltInWidgets.kTextView)
		.getEntry();
*/

	public ControlPanelControllerSubsystem(ColorSensorSubsystem colorSensor) {
		m_colorSensor = colorSensor;
		m_Motor.configFactoryDefault();
		m_Motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

		// Configure nominal outputs, same values as ShooterSubsystem.java
		m_Motor.configNominalOutputForward(0, 10);
		m_Motor.configNominalOutputReverse(0, 10);
		m_Motor.configPeakOutputForward(1, 10);
		m_Motor.configPeakOutputReverse(-1, 10);

		// Configure neutral mode. Other posible mode:coast
		m_Motor.setNeutralMode(NeutralMode.Brake);

		// Configure Velocity closed loop gains in slot1.
		m_Motor.config_kF(0, 1023.0/7200.0, 10);
		m_Motor.config_kP(0, 0.25, 10);
		m_Motor.config_kI(0, 0.001, 10);
		m_Motor.config_kD(0, 20, 10);

		m_Motor.configPulseWidthPeriod_EdgesPerRot(20, 10);

		m_Motor.set(ControlMode.PercentOutput, 0);
	}
	//Turn color sensor data into motor commands
	public void periodic() {
		if (m_colorSensor.getRotations() >= 4) {
			stop();
		}

		if (m_colorSensor.getStopOnColor() == false) {
			stop();
		} else {
			go();
		}
	}

	//make the color weel spin
	public void spin() {	
		System.out.println("ControlPanelControllerSubsytem::spin");
		m_Motor.set(ControlMode.PercentOutput, 1.0 * motorVelocity.getDouble(1.0));		
	}

	//make the color wheel stop spining
	public void stop() {
		m_Motor.set(ControlMode.PercentOutput, 0.0);
	}

	//make the color weel spin
	public void go() {
		m_Motor.set(ControlMode.PercentOutput, 0.2);
	}
}
	