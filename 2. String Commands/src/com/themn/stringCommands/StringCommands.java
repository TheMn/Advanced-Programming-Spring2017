//In The Name of Allah, The Most Beneficent, The Most Merciful
package com.themn.stringCommands;

import java.util.Scanner;

/**
 * <h1> StringCommands </h1>
 * <h2> (Advanced Programming Second Project) </h2>
 * @author Amirhossein Mahdinejad
 * <p> Mail: mahdinejad.amirhossein@gmail.com </p>
 */
public class StringCommands {

    /**
     * This function will help us to know what method should be called
     * @param s {@code String} we want to check it
     * @return the index of Method if is existent and -1 otherwise
     */
    private static int whatMethod(String s){
        String []methods = {"sort", "toLower", "toUpper", "isPalindromes",
                "rotate", "indexOf", "substring", "split"};
        for (int i = 0; i < methods.length; i++) {
            if(s.equals(methods[i]))
                return i;
        }
        return -1;
    }

    /**
     * This function will detect arguments and call methods to do the commands
     * @param s {@code String} input we want to work on it
     */
    private static void callMethod(String s) {
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
     * Main method will set input and call other functions
     * @param args default
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
