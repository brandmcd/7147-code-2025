// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CoralConstants;
import frc.robot.Constants.RollerConstants;

public class ArmSubsystem extends SubsystemBase {
    private final SparkMax armMotor;
  /** Creates a new ArmSubsystem. */
  public ArmSubsystem() {
    armMotor = new SparkMax(CoralConstants.ARM_MOTOR_ID, MotorType.kBrushed); 

    //configure spark

      // Set can timeout. Because this project only sets parameters once on
    // construction, the timeout can be long without blocking robot operation. Code
    // which sets or gets parameters during operation may need a shorter timeout.
    armMotor.setCANTimeout(250);

    // Create and apply configuration for roller motor. Voltage compensation helps
    // the roller behave the same as the battery
    // voltage dips. The current limit helps prevent breaker trips or burning out
    // the motor in the event the roller stalls.
    SparkMaxConfig armConfig = new SparkMaxConfig();
    armConfig.voltageCompensation(10);
    armConfig.smartCurrentLimit(60);
    armConfig.idleMode(SparkMaxConfig.IdleMode.kBrake);
    armMotor.configure(armConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setArmSpeed(double speed)
  {
    armMotor.set(speed);
  }
}
