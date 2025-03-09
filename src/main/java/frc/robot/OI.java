package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class OI {
    private static XboxController driverController = new XboxController(0);
    private static XboxController operaController = new XboxController(1);

    public static XboxController getDriveConroller() {
        return driverController;
    }

    public static XboxController getOperatorContnroller() {
        return operaController;
    }
}
