// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;


/** An example command that uses an example subsystem. */
public class ScriptDriveTank extends CommandBase {
  private final DriveSubsystem m_subsystem;
  private double m_lPower;
  private double m_rPower;
  private float m_seconds;

  /**
   * Creates a new ScriptDriveTank.
   *
   * @param subsystem The subsystem used by this command.
   */
  // public ScriptDriveTank(SubDriveTrain subsystem) {
  //  m_subsystem = subsystem;
  // Use addRequirements() here to declare subsystem dependencies.
  //  addRequirements(subsystem);
  //}

  public ScriptDriveTank(DriveSubsystem subsystem, double lPower, double rPower, float seconds) {
    m_lPower = lPower;
    m_rPower = rPower;
    m_seconds = seconds;
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.print("Drive Tank (DT) - Seconds: ");
    System.out.println(m_seconds);
    System.out.println(m_lPower);
    System.out.println(m_rPower);
    m_subsystem.arcadeDrive(m_lPower, 0);
    System.out.println("Drive Tank (DT) - Finished");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  // @Override
  // public boolean isFinished() {
  //  return this.timeSinceInitialized() > m_seconds;
  // }
}