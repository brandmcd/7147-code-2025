// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.CoralConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.RollerConstants;
import frc.robot.commands.ArmCommand;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.ClimberCommand;
import frc.robot.commands.ClimberUp;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.IntakeWheelCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final CANDriveSubsystem driveSubsystem = new CANDriveSubsystem();
  private final ArmSubsystem armSubsystem = new ArmSubsystem();
  private final Climber climber = new Climber();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  // private final CANRollerSubsystem rollerSubsystem = new CANRollerSubsystem();

  // The driver's controller
  private final CommandXboxController driverController = new CommandXboxController(
      OperatorConstants.DRIVER_CONTROLLER_PORT);

  // The operator's controller
  private final CommandXboxController operatorController = new CommandXboxController(
      OperatorConstants.OPERATOR_CONTROLLER_PORT);

  // The autonomous chooser
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Set up command bindings
    configureBindings();

    // Set the options to show up in the Dashboard for selecting auto modes. If you
    // add additional auto modes you can add additional lines here with
    // autoChooser.addOption
    autoChooser.setDefaultOption("Autonomous", new AutoCommand(driveSubsystem));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Set the A button to run the "RollerCommand" command with a fixed
    // value ejecting the gamepiece while the button is held

    // before
    // operatorController.a()
    // .whileTrue(new RollerCommand(() -> RollerConstants.ROLLER_EJECT_VALUE, () ->
    // 0, rollerSubsystem));

    operatorController.povUp().whileTrue(new ClimberUp(climber));
    operatorController.povDown().whileTrue(new ClimberCommand(climber));

    // Set the default command for the drive subsystem to an instance of the
    // DriveCommand with the values provided by the joystick axes on the driver
    // controller. The Y axis of the controller is inverted so that pushing the
    // stick away from you (a negative value) drives the robot forwards (a positive
    // value). Similarly for the X axis where we need to flip the value so the
    // joystick matches the WPILib convention of counter-clockwise positive
    driveSubsystem.setDefaultCommand(new DriveCommand(
        () -> -driverController.getLeftY() * DriveConstants.SPEED_MULT,
        () -> -driverController.getRightX() * DriveConstants.SPEED_MULT,
        driveSubsystem));

    armSubsystem.setDefaultCommand(new ArmCommand(
        () -> -operatorController.getLeftY() * CoralConstants.ARM_SPEED_MULT,
        armSubsystem));

    // run wheel when right trigger is pressed
    operatorController.rightTrigger().whileTrue(new IntakeWheelCommand(intakeSubsystem));

    intakeSubsystem.setDefaultCommand(new IntakeCommand(intakeSubsystem,
        () -> -operatorController.getRightY() * CoralConstants.INTAKE_ROTATE_SPEEDMULT));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return autoChooser.getSelected();
  }
}
