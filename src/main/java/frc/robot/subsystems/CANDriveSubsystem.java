// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

// Class to drive the robot over CAN
public class CANDriveSubsystem extends SubsystemBase {
  private final TalonSRX leftLeader;
  private final TalonSRX leftFollower;
  private final TalonSRX rightLeader;
  private final TalonSRX rightFollower;

  public CANDriveSubsystem() {
    // create brushed motors for drive
    leftLeader = new TalonSRX(DriveConstants.LEFT_LEADER_ID);
    leftFollower = new TalonSRX(DriveConstants.LEFT_FOLLOWER_ID);
    rightLeader = new TalonSRX(DriveConstants.RIGHT_LEADER_ID);
    rightFollower = new TalonSRX(DriveConstants.RIGHT_FOLLOWER_ID);

    //set followers
    leftFollower.follow(leftLeader);
    rightFollower.follow(rightLeader);

    leftLeader.setInverted(true);
    leftFollower.setInverted(true);
  }

  @Override
  public void periodic() {
  }

  // sets the speed of the drive motors
  public void driveArcade(double xSpeed, double zRotation) {
  leftLeader.set(ControlMode.PercentOutput,xSpeed + zRotation);
  rightLeader.set(ControlMode.PercentOutput,xSpeed - zRotation);
  }
}
