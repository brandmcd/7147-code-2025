package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants.LiftConstants;;


public class LiftSubsystem extends SubsystemBase {
    SparkMax liftMotor;
    SparkMax coralMotor;
    PIDController pid;
    double position;

    public LiftSubsystem() {
        /* Set motor ID */
        liftMotor = new SparkMax(LiftConstants.liftMotor, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
        coralMotor = new SparkMax(LiftConstants.coralMotor, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);

        /* Set current limit */
        SparkMaxConfig liftMotorConfig = new SparkMaxConfig();
        liftMotorConfig.idleMode(IdleMode.kBrake);
        liftMotorConfig.smartCurrentLimit(LiftConstants.liftCurrentLimit);
        liftMotor.configure(liftMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        /* Create PID controller */
        pid = new PIDController(LiftConstants.liftMotorP, LiftConstants.liftMotorP, LiftConstants.liftMotorP);
        pid.setTolerance(LiftConstants.liftTolerance);
    }

    public void update(double position) {
        this.position = position;
        liftMotor.set(pid.calculate(liftMotor.getAbsoluteEncoder().getPosition(), position));
    }

    public boolean atPosition(){
        return Math.abs(position - liftMotor.getAbsoluteEncoder().getPosition()) < LiftConstants.liftTolerance;
    }

    public double getPosition(){
        return liftMotor.getEncoder().getPosition();
    }

    public void setLiftMotorSpeed(double speed) {
        liftMotor.set(speed);
    }
}
