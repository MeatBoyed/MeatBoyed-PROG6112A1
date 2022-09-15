import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * REFERENCES:
 *
 * All Varargs/Optional Params (getUserInt method for example), was Adopted from DelfStack blog posted on 16/05/2021
 * Blog: https://www.delftstack.com/howto/java/java-optional-parameter/
 *
 * All Try/Catch statement, was Adapted from a Java Exceptions blog posted on 13/09/2022
 * Blog: https://www.w3schools.com/java/java_try_catch.asp
 */

/**
 * This class is used as a Helper/Handler for accepting user input.
 * Its used to seperate all logic of handling User Input (Error and Exception handling) from the Products and Main class
 */
public class InputHandler {

    /**
     * Manages gettiing an Integer from the user.
     * @param args
     * @return
     */
    public static int getUserInt(String... args) {
        // 0 is used as an error value, because it is the default value of "input".
        int input = 0;
        Scanner scan = new Scanner(System.in);

        // Checks if the user has passed in a params, if they have assign it to error message.
        // Else, use the default error message.
        String errorMessage = args.length != 0 ? args[0] : "Invalid input, please try again.";

        // Ensures the program doesn't crash when the user inputs a String.
        try {
            input = scan.nextInt();
        }catch (InputMismatchException e) {
           displayErrorMessage(errorMessage);
        }

        return input;
    }

    /**
     * Manages getting a String from the user.
     * @param args
     * @return
     */
    public static String getUserString(String... args) {
        String input = "";
        Scanner scan = new Scanner(System.in);

        // Checks if the user has passed in a params, if they have assign it to error message. Otherwise use the default
        // Error message.
        String errorMessage = args.length != 0 ? args[0] : "Invalid input, please try again.";

        // Ensures the program doesn't crash .
        try {
            input = scan.next();

        }catch (InputMismatchException e) {
            displayErrorMessage(errorMessage);
        }

        return input;
    }

    /**
     * Manages getting a specified allowed Integers from the user.
     * This is a modification of "getUserInt" as it ensures the returned input value is only an
     * allowed Integer, to avoid validation checks within the Products class.
     * @param availableOptions
     * @param errorMessage
     * @return
     */
    public static int getUserIntStrict(int[] availableOptions, String errorMessage) {
        int input = 0;
        boolean valid = false;
        Scanner scan = new Scanner(System.in);

        // Ensures the program doesn't crash when the user inputs a String.
        try {
            input = scan.nextInt();

            // Loops over the availbleOptions & check if the input matches any of them.
            for (int option: availableOptions) {
                if (input == option) valid = true;
            }
        }catch (InputMismatchException e) {
            displayErrorMessage(errorMessage);
        }

        // If valid is true, return the input, else use 0 as the invalid check
        return valid ? input : 0;
    }


    /**
     * Manages displaying error messages to the user.
     * @param errorMessage
     * @return
     */
    public static void displayErrorMessage(String errorMessage) {
        System.out.println("**************************************");
        System.out.println(errorMessage);
        System.out.println("**************************************");
    }
}
