import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Products productManager = new Products();
        Scanner scan = new Scanner(System.in);

        // Potential While Loop to be used

        // Display Start-up Prompts
        System.out.println("BRIGHT FUTURE TECHNOLOGIES APPLICATION\n" +
                "**************************************\n" +
                "Enter (1) to launch menu or any other key to exit");

//        productManager.CaptureProduct();

        // ERROR HANDLING
        int userChoice = InputHandler.getUserInt();

        if (userChoice == 1) productManager.DisplayMenu();

    }
}
