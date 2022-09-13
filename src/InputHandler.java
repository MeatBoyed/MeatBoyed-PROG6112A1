import java.lang.reflect.Array;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class is used as a Helper/Handler for accepting user input.
 * It is used to avoid the re-declaration of a Scanner within the core classes (Main & Products), and handles all
 * necessary error handling to ensure the user never encounters an Exception.
 */
public class InputHandler {

    /**
     * Lines 16 & 19 (Varargs & Optional Params), was Adapted from a DelfStack blog posted on 16/05/2021
     * Blog: https://www.delftstack.com/howto/java/java-optional-parameter/
     */
    public static int getUserInt(String... args) {
        int input = 0;
        Scanner scan = new Scanner(System.in);
        String errorMessage = args.length != 0 ? args[0] : "Invalid input, please try again.";

//        TRY GLOBAL REFERENCES FOR CLASS
//        FIND POSTED DATE
        /**
         * Lines 24 & 30 (Try, Catch statement), was Adapted from a Java Exceptions blog posted on 13/09/2022
         * Blog: https://www.w3schools.com/java/java_try_catch.asp
         */
        try {
            input = scan.nextInt();
        }catch (InputMismatchException e) {
           displayErrorMessage(errorMessage);
        }

        return input;
    }

    public static String getUserString(String... args) {
        String input = "";
        Scanner scan = new Scanner(System.in);
        String errorMessage = args.length != 0 ? args[0] : "Invalid input, please try again.";

        try {
            input = scan.next();

        }catch (InputMismatchException e) {
            displayErrorMessage(errorMessage);
        }

        return input;
    }

    // This is a modification of ".getUserInt", however it checks that the input is valid based on the passed
    // "availableOptions" param. This is done to remove validation checks within in the Product class, and
    // ensures it only does it's intended purpose (which isn't the verification of user input)
    public static int getUserIntStrict(int[] availableOptions, String errorMessage) {
        int input = 0;
        boolean valid = false;
        Scanner scan = new Scanner(System.in);

        try {
            input = scan.nextInt();

            for (int option: availableOptions) {
                if (input == option) valid = true;
            }
        }catch (InputMismatchException e) {
            displayErrorMessage(errorMessage);
        }

        return valid ? input : 0;
    }

    public static void displayErrorMessage(String errorMessage) {
        System.out.println("**************************************");
        System.out.println(errorMessage);
        System.out.println("**************************************");
    }
}
