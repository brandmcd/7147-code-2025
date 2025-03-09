// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.LiftCommand;
import frc.robot.subsystems.TankSubsystem;
import frc.robot.subsystems.LiftSubsystem;
// import frc.robot.subsystems.ClimbSubsystem;

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
  /* Robot Subsystem */
  private final TankSubsystem tankSubsystem = new TankSubsystem();
  private final LiftSubsystem liftSubsystem = new LiftSubsystem();
  // private final ClimbSubsystem climbSubsystem = new ClimbSubsystem();

  /* Robot Command */
  private final DriveCommand driveCommand = new DriveCommand(tankSubsystem);
  private final LiftCommand lifeCommand = new LiftCommand(liftSubsystem, false);
  // private final ClimbCommand climbCommand = new ClimbCommand(climbSubsystem);

  // The driver's controller
  // private final CommandXboxController driverController = new CommandXboxController(
  //     OperatorConstants.DRIVER_CONTROLLER_PORT);

  // The operator's controller
 // private final CommandXboxController operatorController = new CommandXboxController(
   //   OperatorConstants.OPERATOR_CONTROLLER_PORT);

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
    // autoChooser.setDefaultOption("Autonomous", new AutoCommand(tankSubsystem));
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
      //  .whileTrue(new RollerCommand(() -> RollerConstants.ROLLER_EJECT_VALUE, () -> 0, rollerSubsystem));


    // Set the default command for the roller subsystem to an instance of
    // RollerCommand with the values provided by the triggers on the operator
    // controller
   // rollerSubsystem.setDefaultCommand(new RollerCommand(
     //   () -> operatorController.getRightTriggerAxis(),
       // () -> operatorController.getLeftTriggerAxis(),
       // rollerSubsystem));
    
    /* Set lifter key binding */
    new Trigger(() -> OI.getOperatorContnroller().getYButton()).whileTrue(new LiftCommand(liftSubsystem, true));
    new Trigger(() -> OI.getOperatorContnroller().getAButton()).whileTrue(new LiftCommand(liftSubsystem, false));
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
