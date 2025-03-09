// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    /* Set motor ID */
    public static final int leftDriveFront = 3;
    public static final int leftDriveBack = 4;
    public static final int rightDriveFront = 2;
    public static final int rightDriveBack = 1;

    /* Set current limit */
    public static final int driveCurrentLimit = 60;

    /* Set drive multiplier */
    public static final double driveMultiplier = 1;
  }

  public static final class LiftConstants{
    /* Set motor ID */
    public static final int liftMotor = 11;
    public static final int coralMotor = 13;

    /* Set current limit */
    public static final int liftCurrentLimit = 60;

    /* Set PID constants */
    public static final double liftMotorP = 0.1;
    public static final double liftMotorI = 0.0;
    public static final double liftMotorD = 0.0; 
    public static final int liftTolerance = 50; //Ticks

    
    public static final int bottomLimitSwitch = 0;

    //Lift Setpoints
    public static final int low = 0;
    public static final int mid = 0;
    public static final int high = 0; //All three of these should be in ticks
}

  public static final class RollerConstants {
    public static final int ROLLER_MOTOR_ID = 5;
    public static final int ROLLER_MOTOR_CURRENT_LIMIT = 60;
    public static final double ROLLER_MOTOR_VOLTAGE_COMP = 10;
    public static final double ROLLER_EJECT_VALUE = 0.44;
  }

  public static final class OperatorConstants {
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int OPERATOR_CONTROLLER_PORT = 1;
  }
}
