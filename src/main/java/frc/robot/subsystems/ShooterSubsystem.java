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
import com.ctre.phoenix.motorcontrol.can.TalonFX; //Changed from SRX to FX -- could result in some errors... also check output of motors

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {

  private final TalonFX m_shooterMotorTop = new TalonFX(ShooterConstants.kShooterMotorTopPort);
  private final TalonFX m_shooterMotorBottom = new TalonFX(ShooterConstants.kShooterMotorBottomPort);

/*
  private NetworkTableEntry topMotorVelocity = Shuffleboard.getTab("Controls")
  .add("Top Motor", m_shooterMotorTop.getSelectedSensorVelocity())
	.withWidget(BuiltInWidgets.kTextView)
	.withPosition(0, 0)
	.withSize(2, 1)
  .getEntry();  
  
  private NetworkTableEntry bottomMotorVelocity = Shuffleboard.getTab("Controls")
  .add("Bottom Motor", m_shooterMotorBottom.getSelectedSensorVelocity())
	.withWidget(BuiltInWidgets.kTextView)
	.withPosition(2, 0)
	.withSize(2, 1)
	.getEntry();
*/
  public ShooterSubsystem() {
	m_shooterMotorTop.configFactoryDefault();
	m_shooterMotorTop.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

	m_shooterMotorBottom.configFactoryDefault();
	m_shooterMotorBottom.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

	// Configure nominal outputs.
	m_shooterMotorTop.configNominalOutputForward(0, 10);
	m_shooterMotorTop.configNominalOutputReverse(0, 10);
	m_shooterMotorTop.configPeakOutputForward(1, 10);
	m_shooterMotorTop.configPeakOutputReverse(-1, 10);

	m_shooterMotorBottom.configNominalOutputForward(0, 50);
	m_shooterMotorBottom.configNominalOutputReverse(0, 25);
	m_shooterMotorBottom.configPeakOutputForward(1, 10);
	m_shooterMotorBottom.configPeakOutputReverse(-1, 10);
	m_shooterMotorBottom.setSensorPhase(true);

	// Configure neutral mode.
	m_shooterMotorTop.setNeutralMode(NeutralMode.Brake);
	m_shooterMotorBottom.setNeutralMode(NeutralMode.Brake);


	// Configure Velocity closed loop gains in slot1.
/*m_shooterMotorTop.config_kF(0, 1023.0/7200.0, 10);
	m_shooterMotorTop.config_kP(0, 0.25, 10);
	m_shooterMotorTop.config_kI(0, 0.001, 10);
	m_shooterMotorTop.config_kD(0, 20, 10);

	m_shooterMotorBottom.config_kF(0, 1023.0/7200.0, 10);
	m_shooterMotorBottom.config_kP(0, 0.25, 10);
	m_shooterMotorBottom.config_kI(0, 0.001, 10);
	m_shooterMotorBottom.config_kD(0, 20, 10);
*/
	m_shooterMotorTop.config_kF(0, 0.0, 10);
	m_shooterMotorTop.config_kP(0, 1, 10);
	m_shooterMotorTop.config_kI(0, 0, 10);
	m_shooterMotorTop.config_kD(0, 0 , 10);

	m_shooterMotorBottom.config_kF(0, 0.0, 10);
	m_shooterMotorBottom.config_kP(0, 1, 10);
	m_shooterMotorBottom.config_kI(0, 0.0, 10);
	m_shooterMotorBottom.config_kD(0, 0, 10);

	//m_shooterMotorTop.configPulseWidthPeriod_EdgesPerRot(20, 10);
	//m_shooterMotorBottom.configPulseWidthPeriod_EdgesPerRot(20, 10);

	m_shooterMotorTop.configPulseWidthPeriod_EdgesPerRot(1024, 10);
	m_shooterMotorBottom.configPulseWidthPeriod_EdgesPerRot(1024, 10);

	m_shooterMotorTop.set(ControlMode.Velocity, 0);
	m_shooterMotorBottom.set(ControlMode.Velocity, 0);
  }

  public void shoot() {
	System.out.println("ShooterSubsytem::shoot");

	/*
	The if/else statement sets the minimum and maximum values (speed) of the motors.
	-800 is the default maximum value
	-0 is the default minimum value
	-The first if and else statement checks if the value is greater than 800. If it is, it will automatically
	set the motor velocity to 800 (or whatever value is in the parameters).
	-The second statement (else if/else) checks if the value is less than 0. If it is, it will automatically
	set the motor velocity to 0 (or whatever value is in the parameters).
	-This is to make sure someone stupid, aka Sean, will not set the motors to a stupidly high or low value.
	*/


	//m_shooterMotorTop.set(ControlMode.PercentOutput, .88);
	//m_shooterMotorBottom.set(ControlMode.PercentOutput, .83);

	m_shooterMotorTop.set(ControlMode.Velocity, 500);
	m_shooterMotorBottom.set(ControlMode.Velocity, 500);


	/*if (topMotorVelocity.getDouble(1.0) > 2400) {
		m_shooterMotorTop.set(ControlMode.Velocity, 2400);
	} else if (topMotorVelocity.getDouble(1.0) < 0) {
		m_shooterMotorTop.set(ControlMode.Velocity, 0);
	} else {
		m_shooterMotorTop.set(ControlMode.Velocity, 1.0 * topMotorVelocity.getDouble(1.0));
	}

	if (bottomMotorVelocity.getDouble(1.0) > 2400) {
		m_shooterMotorBottom.set(ControlMode.Velocity, 2400);
	} else if (bottomMotorVelocity.getDouble(1.0) < 0) {
		m_shooterMotorBottom.set(ControlMode.Velocity, 0);
	} else {
		m_shooterMotorBottom.set(ControlMode.Velocity, 1.0 * bottomMotorVelocity.getDouble(1.0));
	}
	*/
  }
  public void stopshooter() {
	System.out.println("ShooterSubsystem::stop");

	m_shooterMotorTop.set(ControlMode.Velocity, 0);
	m_shooterMotorBottom.set(ControlMode.Velocity, 0);


//	m_shooterMotorTop.set(ControlMode.PercentOutput, 0.0);
//	m_shooterMotorBottom.set(ControlMode.PercentOutput, 0.0);
	


}
}

	