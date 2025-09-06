//In The Name of Allah, The Most Beneficent, The Most Merciful
package com.themn.stringCommands;

import java.util.Scanner;

/**
 * This class serves as the main entry point for the StringCommands application.
 * It reads a line of input containing string manipulation commands, parses them,
 * and calls the appropriate methods from the {@link Methods} class to execute the commands.
 *
 * @author Amirhossein Mahdinejad
 * @version 1.1
 * @since 2023-04-12
 */
public class StringCommands {

    /**
     * Determines which string manipulation method to call based on a given command string.
     *
     * @param s The command string (e.g., "sort", "toLower").
     * @return An integer representing the index of the method in a predefined list, or -1 if the command is not found.
     */
    public static int whatMethod(String s){
        String []methods = {"sort", "toLower", "toUpper", "isPalindromes",
                "rotate", "indexOf", "substring", "split"};
        for (int i = 0; i < methods.length; i++) {
            if(s.equals(methods[i]))
                return i;
        }
        return -1;
    }

    /**
     * Parses a command string, extracts the arguments, and calls the corresponding method from the {@link Methods} class.
     * The result of the method call is printed to the console.
     *
     * @param s The full command string, including the method name and arguments (e.g., "sort(myString,0,7)").
     */
    public static void callMethod(String s) {
        int parantezBaz = Methods.indexOf(s, "("), parantezBaste = s.length()-1;
        String []arguments = Methods.substring(s, parantezBaz+1, parantezBaste-1).split(",");

        boolean threeArgs = (arguments.length == 3);
        switch (whatMethod(Methods.substring(s, 0, parantezBaz-1))) {
            case 0:
                if(threeArgs)
                    System.out.println(Methods.sort(arguments[0], Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2])));
                else
                    System.out.println(Methods.sort(arguments[0], 0, arguments[0].length()-1));
                break;
            case 1:
                if(threeArgs)
                    System.out.println(Methods.toLower(arguments[0], Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2])));
                else
                    System.out.println(Methods.toLower(arguments[0], 0, arguments[0].length()-1));
                break;
            case 2:
                if(threeArgs)
                    System.out.println(Methods.toUpper(arguments[0], Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2])));
                else
                    System.out.println(Methods.toUpper(arguments[0], 0, arguments[0].length()-1));
                break;
            case 3:
                if(Methods.isPalindromes(arguments[0], arguments[1]))
                    System.out.println("True");
                else
                    System.out.println("False");
                break;
            case 4:
                System.out.println(Methods.rotate(arguments[0], Integer.parseInt(arguments[1])));
                break;
            case 5:
                System.out.println(Methods.indexOf(arguments[0], arguments[1]));
                break;
            case 6:
                System.out.println(Methods.substring(arguments[0], Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2])));
                break;
            case 7:
                System.out.println(Methods.split(arguments[0], arguments[1]));
                break;
            default:
                System.err.println("Methods are not detected");
                break;
        }
    }

    /**
     * The main method, which serves as the entry point for the application.
     * It reads a line of input from the console, splits it into individual commands,
     * and calls {@link #callMethod(String)} for each command.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        String []input = s.split("_");
        for (int i = 0; i < input.length; i++)
            callMethod(input[i]);

        scanner.close();
    }
}
