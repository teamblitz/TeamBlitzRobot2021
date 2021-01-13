      // What is in here will not be used until mounted.
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensorSubsystem extends SubsystemBase {
   /**
   * Change the I2C port below to match the connection of your color sensor
   */
  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  /**
   * A Rev Color Sensor V3 object is constructed with an I2C port as a 
   * parameter. The device will be automatically initialized with default 
   * parameters.
   */
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

/**
   * A Rev Color Match object is used to register and detect known colors. This can 
   * be calibrated ahead of time or during operation.
   * 
   * This object uses a simple euclidian distance to estimate the closest match
   * with given confidence range.
   */

  private final ColorMatch m_colorMatcher = new ColorMatch();

  /**
   * Note: Any example colors should be calibrated as the user needs, these
   * are here as a basic example.
   */
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  private String colorString;
  private String colorStringPrevious;
  private int rotations = 0;
  private int colorCounter = 0;
  private boolean spinUntillColor = true; // Spins until color is found
  private boolean spinMode = false; //True = spin x rotations & False = spin until x color
  private int colorSelector = 3; //Decides what color the color wheel will stop at 1=red 2=Blue 3=Green 4=Yellow
  private String colorController = "Red";
  private boolean resetRotations = false;
  private NetworkTableEntry resetTheRotations;

  //Sets up the color sensor
  public ColorSensorSubsystem() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);   
    resetTheRotations = Shuffleboard.getTab("Control Panel")
  .add("Reset Rotations", false)
  .withWidget(BuiltInWidgets.kToggleSwitch)
  .getEntry();
  }

  public int getRotations() {
    return rotations;
  }

  /*public int getColorSelector() {
    return colorSelector;
  }
  */
  public boolean getResetRotations() {
    return resetRotations;
  }

  public boolean getStopOnColor() {
    return spinUntillColor;
  }

  public void periodic() {
   /**
     * The method GetColor() returns a normalized color value from the sensor and can be
     * useful if outputting the color to an RGB LED or similar. To
     * read the raw color, use GetRawColor().
     * 
     * The color sensor works best when within a few inches from an object in
     * well lit conditions (the built in LED is a big help here!). The farther
     * an object is the more light from the surroundings will bleed into the 
     * measurements and make it difficult to accurately determine its color.
     */
    Color detectedColor = m_colorSensor.getColor();

    /**
     * Run the color match algorithm on our detected color
     */
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    //Detect what color is showing
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    //Determins what color the robot is looking for
    if (colorSelector == 1) {
      colorController = "Red";
    } else if (colorSelector == 2) {
      colorController = "Blue";
    } else if (colorSelector == 3) {
      colorController = "Green";
    } else if (colorSelector == 4) {
      colorController = "Yellow";
    } else {
      colorController = "Unknown";
    }


    if (colorString != colorStringPrevious) {
      if (colorString != "Unknown") {
        if (spinMode == true) {
          //Revolution Counter
          if (colorString == colorController) {
            colorCounter++;
          }
        } else {
          //Spin Until Color
          if (colorString == colorController) {
            spinUntillColor = false;
          }else{
            spinUntillColor = true;
          }
        }
      colorStringPrevious = colorString;
      }
    }

    if (colorCounter == 2) {
      rotations++;
      colorCounter = 0;
    }

    if (resetTheRotations.getBoolean(false) == true) {
      rotations = 0;
      resetRotations = false;
      resetTheRotations.setBoolean(false);
    }

    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the 
     * sensor.
     */
    SmartDashboard.putNumber("Red", detectedColor.red); //Puts color Red in SmartDashboard
    SmartDashboard.putNumber("Green", detectedColor.green); //Puts color Green in SmartDashboard
    SmartDashboard.putNumber("Blue", detectedColor.blue); // Puts color Blue in SmartDashboard
    SmartDashboard.putNumber("Confidence", match.confidence); // Puts RGB value into SmartDashboard
    SmartDashboard.putString("Detected Color", colorString); // Puts what color is seen into SmartDashboard
    SmartDashboard.putNumber("Color Counter", colorCounter); // Puts number of times it has seen the color into SmartDashboard
    SmartDashboard.putNumber("Rotations", rotations); // Puts number of rotations into SmartDashboard
    SmartDashboard.putBoolean("Has color passed", spinUntillColor); // Puts what colors have passed into SmartDashboard
  }
}