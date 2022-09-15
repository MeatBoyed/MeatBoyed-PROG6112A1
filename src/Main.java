import java.util.Scanner;

/**
 * The specified "Main" class from the assignment.
 */
public class Main {

    public static void main(String[] args) {
        Products productManager = new Products();

        // Display Start-up Prompts
        System.out.println("BRIGHT FUTURE TECHNOLOGIES APPLICATION\n" +
                "**************************************\n" +
                "Enter (1) to launch menu or any other key to exit");

        // Using a String, so it doesn't show an error message when the user doesn't input a number
        String userChoice = InputHandler.getUserString();

        // Ensures the correct logic is invoked based on the user's input.
        if (userChoice.equals("1")) productManager.DisplayMenu(); else return;
    }
}
