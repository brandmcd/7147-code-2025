// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.TankSubsystem;
import frc.robot.OI;

// Command to drive the robot with joystick inputs
public class DriveCommand extends Command {
  private final TankSubsystem tankSubsystem;

  // Constructor. Runs only once when the command is first created.
  public DriveCommand(TankSubsystem tankSubsystem) {
    this.tankSubsystem = tankSubsystem;
  }

  // Runs every cycle while the command is scheduled (~50 times per second)
  @Override
  public void execute() {
    /* Get position from joystick */
    double leftDrive = OI.getDriveConroller().getLeftY();
    double rightDrive = OI.getDriveConroller().getRightY();

    /* Avoid drifting at low input */
    leftDrive = Math.abs(leftDrive) < 0.05 ? 0 : leftDrive;
    leftDrive*= DriveConstants.driveMultiplier;
    rightDrive = Math.abs(rightDrive) < 0.05 ? 0 : rightDrive;
    rightDrive *= DriveConstants.driveMultiplier;
    tankSubsystem.driveTank(leftDrive, rightDrive);
  }

  // Runs each time the command ends via isFinished or being interrupted.
  @Override
  public void end(boolean isInterrupted) {
    tankSubsystem.driveTank(0, 0);
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
