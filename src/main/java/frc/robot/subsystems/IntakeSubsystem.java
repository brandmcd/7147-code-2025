// Author: UMN Robotics Ri3D
// Last Updated: January 2025

package frc.robot.subsystems;

import frc.robot.Constants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CoralConstants;

public class IntakeSubsystem extends SubsystemBase {  
  // Intake Motor Controllers
  private SparkMax m_intakeWheel; // NEO 550 motor
  private SparkMax m_intakeRotate; // NEO 550 motor

  private double intakeWheelRPM; // Stores the rpm of the bar
  private double intakeRotatePosition; // Stores the position of the arm


  /** Subsystem for controlling the Intake */
  public IntakeSubsystem() {
    // Instantiate the Intake motor controllers
    m_intakeRotate = new SparkMax(CoralConstants.INTAKE_ROTATE_ID, MotorType.kBrushless);
    m_intakeWheel = new SparkMax(CoralConstants.INTAKE_WHEEL_ID, MotorType.kBrushless);

    
    SparkMaxConfig config = new SparkMaxConfig();
    config.idleMode(IdleMode.kBrake);
    m_intakeWheel.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_intakeRotate.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    intakeWheelRPM = m_intakeWheel.getEncoder().getVelocity();
    intakeRotatePosition = m_intakeRotate.getEncoder().getPosition();


    // Add intake bar RPM and deploy position readings to SmartDashboard for the sake of data logging
    SmartDashboard.putNumber("Intake Bar RPM", intakeWheelRPM);
    SmartDashboard.putNumber("Intake Arm Position", intakeRotatePosition);
  }

  /* Set power to the intake motor */
  public void setintakeWheelPower(double power) {
    m_intakeWheel.set(power);
  }
  public void stopintakeWheel() {
    m_intakeWheel.set(0);
  }

  /* Read the speed of the intake motor */
  public double getintakeWheelRPM() {
    return intakeWheelRPM;
  }

  /* get position */
  public double getintakeRotatePosition() {
    return intakeRotatePosition;
  }

  public void setintakeRotatePower(double power) {
    m_intakeRotate.set(power);
  }

  public void stopintakeRotate() {
    setintakeRotatePower(0);
  }

   /** This is a method that makes the roller spin */
   public void runRoller(double forward, double reverse) {
    m_intakeRotate.set(forward - reverse);
  }
}