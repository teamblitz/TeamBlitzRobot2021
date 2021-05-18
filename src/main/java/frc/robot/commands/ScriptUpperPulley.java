// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.UpperPulleySubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/** An example command that uses an example subsystem. */
public class ScriptUpperPulley extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final UpperPulleySubsystem m_subsystem;
  private float m_seconds;

  /**
   * Creates a new ScriptFeeder.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ScriptUpperPulley(UpperPulleySubsystem subsystem, float seconds) {
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
    System.out.print("Feed Forward (FF) - Seconds: ");
    System.out.println(m_seconds);
    m_subsystem.upPulley();
    Timer.delay(m_seconds);
    m_subsystem.stopPulley();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}