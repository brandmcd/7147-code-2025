// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  private final SparkMax ClimberMotor;
  
  /** Creates a new Climber. */
  public Climber() {
    ClimberMotor = new SparkMax(6,MotorType.kBrushed);

    SparkMaxConfig config = new SparkMaxConfig();
    config.voltageCompensation(12);
    //set to brake mode
    config.idleMode(SparkMaxConfig.IdleMode.kBrake);

    ClimberMotor.configure(
    config,
    SparkMax.ResetMode.kNoResetSafeParameters,
    SparkMax.PersistMode.kPersistParameters);
  } 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  public void runClimber(double speed) {
    ClimberMotor.set(speed);
  }
}
