// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;
import java.util.function.DoubleSupplier;

// Command to drive the robot with joystick inputs
public class ArmCommand extends Command {
  private final DoubleSupplier spoolSpeed;
  private final ArmSubsystem armSubsystem;

  // Constructor. Runs only once when the command is first created.
  public ArmCommand(DoubleSupplier spoolSpeed, ArmSubsystem armSubsystem) {
    this.spoolSpeed = spoolSpeed;
    this.armSubsystem = armSubsystem;

    addRequirements(armSubsystem);
  }

  // Runs each time the command is scheduled.
  @Override
  public void initialize() {
  }

  // Runs every cycle while the command is scheduled (~50 times per second)
  @Override
  public void execute() {
    if (Math.abs(spoolSpeed.getAsDouble()) > 0.05) {
      armSubsystem.setArmSpeed(spoolSpeed.getAsDouble());
    }
  }

  // Runs each time the command ends via isFinished or being interrupted.
  @Override
  public void end(boolean isInterrupted) {
  }

  // Runs every cycle while the command is scheduled to check if the command is
  // finished
  @Override
  public boolean isFinished() {
    // Return false to indicate that this command never ends. It can be interrupted
    // by another command needing the same subsystem.
    return false;
  }
}
