package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.robot.Constants.DriveConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankSubsystem extends SubsystemBase {
    SparkMax leftDriveFront;
    SparkMax leftDriveBack;
    SparkMax rightDriveFront;
    SparkMax rightDriveBack;

    public TankSubsystem() {
        /* Create SparkMax list */
        leftDriveFront = new SparkMax(DriveConstants.leftDriveFront, MotorType.kBrushed);
        leftDriveBack = new SparkMax(DriveConstants.leftDriveBack, MotorType.kBrushed);
        rightDriveFront = new SparkMax(DriveConstants.rightDriveFront, MotorType.kBrushed);
        rightDriveBack = new SparkMax(DriveConstants.rightDriveBack, MotorType.kBrushed);

        /* Set left front motor configuration */
        SparkMaxConfig leftDriveFrontConfig = new SparkMaxConfig();
        leftDriveFrontConfig.idleMode(IdleMode.kCoast);
        leftDriveFrontConfig.smartCurrentLimit(DriveConstants.driveCurrentLimit);
        leftDriveFront.configure(leftDriveFrontConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        /* Set left back motor configuration */
        SparkMaxConfig leftDriveBackConfig = new SparkMaxConfig();
        leftDriveBackConfig.idleMode(IdleMode.kCoast);
        leftDriveBackConfig.smartCurrentLimit(DriveConstants.driveCurrentLimit);
        leftDriveBackConfig.follow(leftDriveFront);
        leftDriveBack.configure(leftDriveBackConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        /* Set right front motor configuration */
        SparkMaxConfig rightDriveFrontConfig = new SparkMaxConfig();
        rightDriveFrontConfig.idleMode(IdleMode.kCoast);
        rightDriveFrontConfig.smartCurrentLimit(DriveConstants.driveCurrentLimit);
        rightDriveFront.configure(rightDriveFrontConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        /* Set right back motor configuration */
        SparkMaxConfig rightDriveBackConfig = new SparkMaxConfig();
        rightDriveBackConfig.idleMode(IdleMode.kCoast);
        rightDriveBackConfig.smartCurrentLimit(DriveConstants.driveCurrentLimit);
        rightDriveBackConfig.follow(rightDriveFront);
        rightDriveBack.configure(rightDriveBackConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void driveTank(double leftSpeed, double rightSpeed) {
        leftDriveFront.set(leftSpeed);
        rightDriveFront.set(rightSpeed);
    }
}
