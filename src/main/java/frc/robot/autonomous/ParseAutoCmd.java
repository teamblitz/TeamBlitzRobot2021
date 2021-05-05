package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.commands.ScriptDriveTank;

public class ParseAutoCmd extends SequentialCommandGroup {    

    DriveSubsystem m_DriveSubsystem = new DriveSubsystem();
    FeederArmSubsystem m_FeederArmSubsystem = new FeederArmSubsystem();
    FeederWheelsSubsystem m_FeederWheelsSubsystem = new FeederWheelsSubsystem();
    ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();

    public SequentialCommandGroup parseAutoCmds () {
        String tempScript;
        String[] commands;
        
        SequentialCommandGroup autoSeqCommands = new  SequentialCommandGroup();

        // get script from shuffleboard, string for now, P implies include the next command in parallel, can have >1 P commands in sequence
        tempScript = "DT(50,50,4);FF(5)P;SH(5);";
        // remove whitespace, convert to uppercase
        tempScript = tempScript.replaceAll("\\s+","");
        tempScript.toUpperCase();


        ScriptValidator sv = new ScriptValidator();
        if (!sv.isValid(tempScript)) 
            return null;

        // split commands by delimiter
        commands = tempScript.split(";");

        for (int i=0; i < commands.length; i++) {
            
            if (commands[i].contains(")P")) {
                // process parallel commands
                // Get parallel command group object and add commands for each in parallel command
                ParallelCommandGroup parallelCommands = new  ParallelCommandGroup();

                do {
                    addScriptCommand(parallelCommands, commands[i]);
                    i++;
                } while (commands[i-1].contains(")P"));  // keep adding parallel commands while the previous command has a P

                // add the group of parallel commands to the overall sequence command group
                autoSeqCommands.addCommands(parallelCommands);
            }
            else {
                // process sequential command
                addScriptCommand(autoSeqCommands, commands[i]);                                         
            } 
        }
        return autoSeqCommands;
    }

    public void addScriptCommand(CommandGroupBase commandList, String command) {
        String scriptCommand;
        String paramList;
        String[] params;

        scriptCommand = command.substring(0, command.indexOf('('));
        paramList = command.substring(command.indexOf('(')+1, command.indexOf(')'));
        params = paramList.split(",");

        // NEW SCRIPT COMMANDS ADDED HERE *******************************************************************************
        switch (scriptCommand) {
            case "DT":                        
                commandList.addCommands(new ScriptDriveTank(m_DriveSubsystem, Float.parseFloat(params[0]), Float.parseFloat(params[1]), Float.parseFloat(params[2])));
                break;
            // case "FF":
            //     commandList.addCommands(new upFeeder(m_SubFeeder, Float.parseFloat(params[0])));
            //     break;
            // case "SH":
            //     commandList.addCommands(new ScriptShooter(m_SubShooter, Float.parseFloat(params[0])));
            //     break;
            default:
                throw new IllegalArgumentException("unknown script command : " + scriptCommand);
        }    
        
    }
}
