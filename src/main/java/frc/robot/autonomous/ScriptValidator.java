package frc.robot.autonomous;

import java.util.*;

public class ScriptValidator {
    // SL SLeep
    // DT Drive Tank
    // FF Feed Forward
    // SH Shooter

    // NEW SCRIPT COMMANDS ADDED HERE *******************************************************************************
    private static final String[] VALID_COMMAND = {"SL", "DT", "FF", "SH"};
    private static final int[] VALID_PARAMS =     {  1,    3,    1,    1 } ;
    // **************************************************************************************************************

    public boolean isValid(String scriptString) {
        String[] commands;
        String scriptCommand;
        String paramList;
        String[] params;
        int pPos;

        //Check for valid script

        try {                

            commands = scriptString.split(";");

            for (int i=0; i < commands.length; i++) {
                // get script command prior to open paren                
                scriptCommand = commands[i].substring(0,commands[i].indexOf('('));
                
                // check for valid script command against valid list
                if (!(Arrays.asList(VALID_COMMAND).contains(scriptCommand))) {                
                    System.out.print("invalid script command"+"\n");
                    return false;
                }
                
                paramList = commands[i].substring(commands[i].indexOf('(')+1, commands[i].indexOf(')'));                
                params = paramList.split(",");

                // check # of parameters for command against valid list
                if (params.length != VALID_PARAMS[Arrays.asList(VALID_COMMAND).indexOf(scriptCommand)]) {                    
                    System.out.print("invalid number of parameters "+"\n");
                    return false;
                }

                //check for non number or blank param
                for (i=0; i < params.length; i++){
                    if (!params[i].matches("[0-9]+")) {
                        System.out.print("parameter not a number or missing "+"\n");
                        return false;
                    }
                }
                
                // 1 set open and close parens
                if (commands[i].indexOf('(') < 0 || commands[i].indexOf(')') < 0 ||  commands[i].indexOf('(') > commands[i].indexOf(')')) {
                    System.out.print("mismatching parens "+"\n");
                    return false;
                }

                //only numbers and commas in parens
                if (!paramList.matches("^((\\s*-?\\d*(.\\d*)?\\s*)(,|$))+")) {
                    System.out.print("parameters may be only numbers"+"\n");
                    return false;
                }

                // check if there is a P, its the last character and its not the last script command
                pPos = commands[i].indexOf('P');
                
                if (pPos > 0 && (pPos < commands[i].length()-1 || i >= commands.length-1)) {
                    System.out.print("P placement issue"+ "\n");
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.print("System Error"+ "\n");
            return false;
        }


        return true;
    }

}

