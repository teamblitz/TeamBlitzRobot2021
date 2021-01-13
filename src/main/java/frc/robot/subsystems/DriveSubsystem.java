/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {

  /* Master Talons */
  private final WPI_TalonFX m_leftMaster = new WPI_TalonFX(Constants.DriveConstants.kLeftMasterPort);
  private final WPI_TalonFX m_rightMaster = new WPI_TalonFX(Constants.DriveConstants.kRightMasterPort);
  /* Slave Talons */
  private final WPI_TalonFX m_leftSlave = new WPI_TalonFX(Constants.DriveConstants.kLeftSlavePort);
  private final WPI_TalonFX m_rightSlave = new WPI_TalonFX(Constants.DriveConstants.kRightSlavePort);
  
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMaster, m_rightMaster);
  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
    /* Factory Default all hardware to prevent unexpected behaviour */
    m_leftMaster.configFactoryDefault();
    m_rightMaster.configFactoryDefault();
    m_leftSlave.configFactoryDefault();
    m_rightSlave.configFactoryDefault();

    // Peak and Nominal Output
    //m_leftMaster.configPeakOutputForward(1.0);
    //m_rightMaster.configPeakOutputForward(1.0);
    // m_leftMaster.configNominalOutputForward(0.1);
    // m_rightMaster.configNominalOutputForward(0.1);

    // Current Limits
    // double kStatorCurrentLimit = 35;
    // double kStatorTriggerThreshold = 40;
    // double kStatorTriggerThresholdTime = 1.0;
    // double kSupplyCurrentLimit = 35;
    // double kSupplyTriggerThreshold = 40;
    // double kSupplyTriggerThresholdTime = 0.5;

    // m_leftMaster.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, kStatorCurrentLimit, kStatorTriggerThreshold, kStatorTriggerThresholdTime));
    // m_leftMaster.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, kSupplyCurrentLimit, kSupplyTriggerThreshold, kSupplyTriggerThresholdTime));
    // m_rightMaster.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, kStatorCurrentLimit, kStatorTriggerThreshold, kStatorTriggerThresholdTime));
    // m_rightMaster.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, kSupplyCurrentLimit, kSupplyTriggerThreshold, kSupplyTriggerThresholdTime));
    // m_leftSlave.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, kStatorCurrentLimit, kStatorTriggerThreshold, kStatorTriggerThresholdTime));
    // m_leftSlave.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, kSupplyCurrentLimit, kSupplyTriggerThreshold, kSupplyTriggerThresholdTime));
    // m_rightSlave.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, kStatorCurrentLimit, kStatorTriggerThreshold, kStatorTriggerThresholdTime));
    // m_rightSlave.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, kSupplyCurrentLimit, kSupplyTriggerThreshold, kSupplyTriggerThresholdTime));

    // Neutral Mode
    m_leftMaster.setNeutralMode(NeutralMode.Coast);
    m_rightMaster.setNeutralMode(NeutralMode.Coast);
    m_leftSlave.setNeutralMode(NeutralMode.Coast);
    m_rightSlave.setNeutralMode(NeutralMode.Coast);

    // Make the motors ramp up slowly.
   // m_leftMaster.configOpenloopRamp(1.0, 10); //Fisrt numer is the number of seconds it takes to ramp up and don't touch the second
   // m_rightMaster.configOpenloopRamp(1.0, 10);
    
    
    // *********** PUT NON-TUNABLE PARAMETERS BELOW THIS LINE **********

    /**
    * Take our extra motor controllers and have them
    * follow the Talons updated in arcadeDrive 
    */
    m_leftSlave.follow(m_leftMaster);
    m_rightSlave.follow(m_rightMaster);

    /**
    * Drive robot forward and make sure all motors spin the correct way.
    * Toggle booleans accordingly.... 
    */
    m_leftMaster.setInverted(TalonFXInvertType.Clockwise);          // <<<<<< Adjust this until robot drives forward when stick is forward
    m_rightMaster.setInverted(TalonFXInvertType.CounterClockwise);  // <<<<<< Adjust this until robot drives forward when stick is forward
    m_leftSlave.setInverted(InvertType.FollowMaster);
    m_rightSlave.setInverted(InvertType.FollowMaster);
    
    /* diff drive assumes (by default) that 
      right side must be negative to move forward.
      Change to 'false' so positive/green-LEDs moves robot forward  
    */
    m_drive.setRightSideInverted(false); // do not change this
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
 /* public void arcadeDrive(final double fwd, final double rot) {
    System.out.println("arcadeDrive");
    m_drive.arcadeDrive(fwd, rot);
  }
  */
  public void tankDrive(final double leftSpeed, final double rightSpeed) {
    System.out.println("i am speed");
    // Instead of calling tankDrive, call set(ControlMode.Velocity, ...) on each master motor directly. 
    m_drive.tankDrive(leftSpeed, rightSpeed);
  }
}