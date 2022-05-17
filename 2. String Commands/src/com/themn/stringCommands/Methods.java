package com.themn.stringCommands;

/**
 * <h1> Methods </h1>
 * In this class all StringCommands have been defined
 */
public class Methods{

    /**
     * This method will swap two index of String
     * @param s the first input is {@code String} we want to work on it
     * @param i the first index {@code int}
     * @param j the second index {@code int}
     * @return result is the {@code String} index i-th and j-th of input are swaped
     */
    private static String swaped(String s, int i, int j){
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
     * This method will check if index is in interval
     * @param index {@code int} that we want check it
     * @param size {@code int} array length
     * @return A boolean shows if index is ok or not
     */
    private static boolean isOk(int index, int size) {
        return (index >= 0 && index <= size-1);
    }

    /**
     * This method will sort String from start index to finish index
     * @param s the input {@code String} we want to work on it
     * @param start {@code int} the start index
     * @param finish {@code int} the finish index
     * @return sorted input from start to finish
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
     * This method will change capital letters to Lowercase in the interval
     * @param s the input {@code String} we want to work on it
     * @param start {@code int} the start index
     * @param finish {@code int} the finish index
     * @return res {@code String} all characters in interval [start, finish] are in lowercase form
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
     * This method will change Lowercase letters in the interval to Capital
     * @param s the input {@code String} we want to work on it
     * @param start {@code int} the start index
     * @param finish {@code int} the finish index
     * @return res {@code String} all characters in interval [start, finish] are in uppercase form
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
     * Check if two String are Palindromes
     * @param first {@code String}
     * @param second {@code String}
     * @return A boolean shows if first and second are Palindromes or not
     */
    public static boolean isPalindromes(String first, String second) {
        String firstReversed = "";
        for (int i = first.length()-1; i >= 0; i--)
            firstReversed += first.charAt(i);
        return (firstReversed.equals(second));
    }

    /**
     * This method will rotate String to right or left
     * @param s is the {@code String} input we want to rotate it
     * @param turn {@code int} shows that how many times in which direction rotating
     * @return rotated form of input String
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
     * This method will return a sub-string
     * @param s is the {@code String} we want to work on it
     * @param start is the {@code int} value start index of sub-string
     * @param finish is the {@code int} value finish index of sub-string
     * @return res is sub-string from index start to finish
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
     * This method will return index of String in another one
     * @param mainString {@code String} is the elder one
     * @param subString {@code String} is the smaller one
     * @return index of subString in mainString if is existent and -1 otherwise
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
     * This method will replace all extra characters of String with underline
     * @param mainString {@code String} is the elder one
     * @param overflow {@code String} is the smaller one
     * @return String replaced extra characters with underline
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
