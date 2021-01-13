/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
* The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
* constants.  This class should not be used for any other purpose.  All constants should be
* declared globally (i.e. public static).  Do not put anything functional in this class.
*
* <p>It is advised to statically import this class (or one of its inner classes) wherever the
* constants are needed, to reduce verbosity.
*/
public final class Constants {
    public static final class ShooterConstants {
        public static final int[] kEncoderPorts = new int[]{2, 3};
        public static final boolean kEncoderReversed = false;
        public static final int kEncoderCPR = 1024;
        public static final double kEncoderDistancePerPulse =
        // Distance units will be rotations
        1.0 / (double) kEncoderCPR;
        
        public static final int kShooterMotorTopPort = 5;
        public static final int kShooterMotorBottomPort = 4;
        // Ports the shooters are attatched to.
        
        public static final double kShooterFreeRPS = 5300;
        public static final double kShooterTargetRPS = 4000;
        public static final double kShooterToleranceRPS = 50;
        // How many rotations per second should be achieved.
        
        // These are not real PID gains, and will have to be tuned for your specific robot.
        public static final double kP = 1;
        public static final double kI = 0;
        public static final double kD = 0;
        
        // On a real robot the feedforward constants should be empirically determined; these are
        // reasonable guesses.
        public static final double kSVolts = 0.05;
        public static final double kVVoltSecondsPerRotation = 12.0 / kShooterFreeRPS;   // Should have value 12V at free speed.
    }

    public static final class ControlPanelControllerConstants {
        public static final int[] kEncoderPorts = new int[]{2, 3};
        public static final boolean kEncoderReversed = false;
        public static final int kEncoderCPR = 1024;
        public static final double kEncoderDistancePerPulse =
            // Distance units will be rotations
            1.0 / (double) kEncoderCPR;

        public static final int kMotorPort = 35;
        
        public static final double kFreeRPS = 5300;
        public static final double kTargetRPS = 4000;
        public static final double kToleranceRPS = 50;

        // These are not real PID gains, and will have to be tuned for your specific robot.
        public static final double kP = 1;
        public static final double kI = 0;
        public static final double kD = 0;

        // On a real robot the feedforward constants should be empirically determined; these are
        // reasonable guesses.
        public static final double kSVolts = 0.05;
        public static final double kVVoltSecondsPerRotation = 12.0 / kFreeRPS;      // Should have value 12V at free speed.
    }

    public static final class DriveConstants {     
        public static final int kRightMasterPort = 8;
        public static final int kRightSlavePort = 7;

        public static final int kLeftMasterPort = 6;
        public static final int kLeftSlavePort = 9;
    } 

    public static final class OIConstants {
        public static final int kDriveControllerPort = 0;
        public static final int kAncillaryControlerPort = 1;

        // Xbox buttons:
        public static final int kOverdriveLeftTriggerAxis = 2;
        public static final int kOverdriveRightTriggerAxis = 3;

        // Joystick buttons:
        // If you enable the toggle command then it will use one button
        public static final int kFeederArmToggleButton = 11;
        // If you enable the two buttons for down and up you have to use one
        // for down and one for up
        public static final int kFeederArmDownButton = 11;
        public static final int kFeederArmUpButton = 9;

        //Works about the same as the kFeederArmSubsystem, not sure if the button IDs used exist though.
        public static final int kStopPulley = 4;
        public static final int kUpperPulleyButtonUp = 5;
        public static final int kUpperPulleyButtonDown = 6;
        public static final int kFeederIntakeToggleBack = 12;
        public static final int kFeederIntakeToggleButton = 3;
        public static final int kFeederIntakeToggleOff = 4;
        public static final int kShooterToggleButton = 1;
        public static final int kShooterToggleOff = 2;
       // public static final int kControlPanelSpinToColorButton = 4;
       // public static final int kControPanelMultiRotationsButton =  5;
    }

    public static final class FeederSubsystemConstants {
        public static final int kSparkMotorPortIntakeArm = 11;
    }

    public static final class FeederWheelsSubsystemConstants {
        public static final int kSparkMotorPortIntakeRoller = 10;
    }
    public static final class UpperPulleySubsystemConstants {
        public static final int kUpperPulleyPort = 3;
    }
}