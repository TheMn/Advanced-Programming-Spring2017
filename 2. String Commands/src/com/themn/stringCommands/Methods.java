package com.themn.stringCommands;

/**
 * This class provides a collection of utility methods for string manipulation.
 * These methods include sorting, case conversion, palindrome checking, rotation, and more.
 *
 * @author Amirhossein Mahdinejad
 * @version 1.1
 * @since 2023-04-12
 */
public class Methods{

    /**
     * Swaps two characters in a string at the specified indices.
     *
     * @param s The input string.
     * @param i The index of the first character to swap.
     * @param j The index of the second character to swap.
     * @return A new string with the characters at indices i and j swapped.
     */
    public static String swaped(String s, int i, int j){
        char []characters = new char[s.length()];
        for (int c = 0; c < characters.length; c++)
            characters[c] = s.charAt(c);
        characters[i] = s.charAt(j);
        characters[j] = s.charAt(i);
        String res = "";
        for (int c = 0; c < characters.length; c++) {
            res += characters[c];
        }
        return res;
    }

    /**
     * Checks if an index is within the valid bounds of a given size.
     *
     * @param index The index to check.
     * @param size The size of the array or string.
     * @return {@code true} if the index is valid, {@code false} otherwise.
     */
    public static boolean isOk(int index, int size) {
        return (index >= 0 && index <= size-1);
    }

    /**
     * Sorts a portion of a string in ascending order (case-insensitive).
     *
     * @param s The input string.
     * @param start The starting index of the portion to sort (inclusive).
     * @param finish The ending index of the portion to sort (inclusive).
     * @return A new string with the specified portion sorted.
     */
    public static String sort(String s, int start, int finish){
        for (int i = start; i <= finish; i++) {
            for (int j = i; j <= finish; j++) {
                char a = (s.charAt(i)<='Z' && s.charAt(i)>='A')? (char)(s.charAt(i)+('a'-'A')): s.charAt(i);
                char b = (s.charAt(j)<='Z' && s.charAt(j)>='A')? (char)(s.charAt(j)+('a'-'A')): s.charAt(j);
                if(a > b)
                    s = swaped(s, i, j);
            }
        }
        return s;
    }

    /**
     * Converts all uppercase characters in a specified portion of a string to lowercase.
     *
     * @param s The input string.
     * @param start The starting index of the portion to convert (inclusive).
     * @param finish The ending index of the portion to convert (inclusive).
     * @return A new string with the specified portion converted to lowercase.
     */
    public static String toLower(String s, int start, int finish){
        String res = "";
        char []characters = new char[s.length()];
        for (int i = 0; i < characters.length; i++){
            char c, presentChar = s.charAt(i);
            if(i<=finish && i>=start){
                if(presentChar<='Z' && presentChar>='A'){
                    int distance = (int)'a' - (int)'A';
                    c = (char)((int)presentChar + distance);
                }
                else
                    c = presentChar;
            }
            else
                c = presentChar;
            characters[i] = c;
        }
        for (int i = 0; i < characters.length; i++)
            res += characters[i];
        return res;
    }

    /**
     * Converts all lowercase characters in a specified portion of a string to uppercase.
     *
     * @param s The input string.
     * @param start The starting index of the portion to convert (inclusive).
     * @param finish The ending index of the portion to convert (inclusive).
     * @return A new string with the specified portion converted to uppercase.
     */
    public static String toUpper(String s, int start, int finish) {
        String res = "";
        char []characters = new char[s.length()];
        for (int i = 0; i < characters.length; i++){
            char c, presentChar = s.charAt(i);
            if(i<=finish && i>=start){
                if(presentChar<='z' && presentChar>='a'){
                    int distance = (int)'a' - (int)'A';
                    c = (char)((int)presentChar - distance);
                }
                else
                    c = presentChar;
            }
            else
                c = presentChar;
            characters[i] = c;
        }
        for (int i = 0; i < characters.length; i++)
            res += characters[i];
        return res;
    }

    /**
     * Checks if a string is the reverse of another, effectively checking if they are palindromic to each other.
     *
     * @param first The first string.
     * @param second The second string.
     * @return {@code true} if the first string is the reverse of the second, {@code false} otherwise.
     */
    public static boolean isPalindromes(String first, String second) {
        String firstReversed = "";
        for (int i = first.length()-1; i >= 0; i--)
            firstReversed += first.charAt(i);
        return (firstReversed.equals(second));
    }

    /**
     * Rotates a string to the right or left by a specified number of turns.
     *
     * @param s The string to rotate.
     * @param turn The number of positions to rotate. A positive value rotates to the right, a negative value rotates to the left.
     * @return The rotated string.
     */
    public static String rotate(String s, int turn) {
        String res = "";
        char []characters = new char[s.length()];
        if(turn<0){
            turn = -turn;
            for (int i = 0; i < characters.length; i++){
                characters[i] = s.charAt((i+turn)%s.length());
            }
        }
        else{
            for (int i = 0; i < characters.length; i++){
                characters[(i+turn)%s.length()] = s.charAt(i);
            }
        }
        for (int i = 0; i < characters.length; i++)
            res += characters[i];
        return res;
    }

    /**
     * Extracts a substring from a string based on start and end indices.
     *
     * @param s The input string.
     * @param start The starting index of the substring (inclusive).
     * @param finish The ending index of the substring (inclusive).
     * @return The extracted substring.
     */
    public static String substring(String s, int start, int finish) {
        String res = "";
        if(isOk(start, s.length()) && isOk(finish, s.length()))
            for (int i = start; i <= finish; i++) {
                res += s.charAt(i);
            }
        return res;
    }

    /**
     * Finds the first occurrence of a substring within a main string.
     *
     * @param mainString The string to search in.
     * @param subString The substring to search for.
     * @return The starting index of the first occurrence of the substring, or -1 if not found.
     */
    public static int indexOf(String mainString, String subString) {
        for (int i = 0; i < mainString.length(); i++) {
            if(substring(mainString, i, i+subString.length()-1).equals(subString)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Replaces all occurrences of a specified substring with an underscore.
     *
     * @param mainString The main string.
     * @param overflow The substring to be replaced.
     * @return A new string with all occurrences of the substring replaced by underscores.
     */
    public static String split(String mainString, String overflow) {
        String tmp = mainString;
        while(indexOf(tmp, overflow) != -1){
            int index = indexOf(tmp, overflow);
            tmp = substring(tmp, 0, index-1) + "_" + substring(tmp, index+overflow.length(), tmp.length()-1);
        }
        return tmp;
    }

}
