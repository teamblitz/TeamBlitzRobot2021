// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ScriptShooter extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem m_subsystem;
  private float m_topPower;
  private float m_bottomPower;
  private float m_seconds;

  /**
   * Creates a new ScriptShooter.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ScriptShooter(ShooterSubsystem subsystem, float topPower, float bottomPower, float seconds) {
    m_topPower = topPower;
    m_bottomPower = bottomPower;
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
    System.out.print("Shoot Ball (SH) - Seconds: ");
    System.out.println(m_topPower);
    System.out.println(m_bottomPower);
    System.out.println(m_seconds);
    m_subsystem.shoot(m_topPower, m_bottomPower);
    Timer.delay(m_seconds);
    m_subsystem.shoot(0, 0);
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