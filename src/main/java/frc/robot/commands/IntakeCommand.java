// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

import java.util.function.DoubleSupplier;

// Command to run the roller with joystick inputs
public class IntakeCommand extends Command {
  private final DoubleSupplier forward;
  private final DoubleSupplier reverse;
  private final DoubleSupplier armSupplier;
  private final IntakeSubsystem intakeSubsystem;

  public IntakeCommand(
      DoubleSupplier forward, DoubleSupplier reverse, DoubleSupplier armSupplier, IntakeSubsystem intakeSubsystem) {
    this.forward = forward;
    this.reverse = reverse;
    this.armSupplier = armSupplier;
    this.intakeSubsystem = intakeSubsystem;

    addRequirements(this.intakeSubsystem);
  }

  @Override
  public void initialize() {
  }

  // Runs every cycle while the command is scheduled (~50 times per second)
  @Override
  public void execute() {
    // Run the roller motor at the desired speed
   intakeSubsystem.runRoller(forward.getAsDouble(), reverse.getAsDouble());
   //run the arm
   if(Math.abs(armSupplier.getAsDouble()) > 0.05)
   {
    intakeSubsystem.setintakeRotatePower(armSupplier.getAsDouble());
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
