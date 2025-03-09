package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LiftSubsystem;

public class LiftCommand extends Command {
    private final LiftSubsystem liftSubsystem;
    private final boolean direction;

    public LiftCommand(LiftSubsystem liftSubsystem, boolean direction) {
        this.liftSubsystem = liftSubsystem;
        this.direction = direction;
    }

    @Override
    public void execute() {
        if (direction) {
            liftSubsystem.setLiftMotorSpeed(0.15);
        } else {
            liftSubsystem.setLiftMotorSpeed(-0.15);
        }
    }

    @Override
    public void end(boolean interrupted) {
        liftSubsystem.setLiftMotorSpeed(0);
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}
