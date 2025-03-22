package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {
  private final IntakeSubsystem intakeSubsystem;
  private final DoubleSupplier speedSupplier;

  /** Creates a new IntakeCommand. */
  public IntakeCommand(IntakeSubsystem intakeSubsystem, DoubleSupplier speedSupplier) {
    this.intakeSubsystem = intakeSubsystem;
    this.speedSupplier = speedSupplier;
    addRequirements(intakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = speedSupplier.getAsDouble();
    if(Math.abs(speed) < 0.1) {
      speed = 0; // Stop the intake if the speed is very low
    }
    intakeSubsystem.setintakeRotatePower(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.setintakeRotatePower(0); // Stop the intake when the command ends
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
