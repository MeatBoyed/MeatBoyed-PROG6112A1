import java.util.List;
import java.util.ArrayList;

/**
 * All Labeling of While Loop ("displayMenuLoop" for example), was Adapted from a Java Point blog posted on UNKNOWN
 * Blog: https://www.javatpoint.com/labeled-loop-in-java
 * These Labeled While Loops are used to easily reference loops when trying to break a loop within a switch statement
 * as there is a keyword collision.
 */
public class Products {

    // Manages storing of all created Product(s)
    public List<Product> Products = new ArrayList<Product>();

    // Manages all functionality of the Main Menu
    public void DisplayMenu() {
        displayMenuLoop: while (true) {
            System.out.println("**************************************");
            System.out.println("Please select one of the following menu items:\n" +
                    "(1) Capture a new product.\n" +
                    "(2) Search for a product.\n" +
                    "(3) Update a product.\n" +
                    "(4) Delete a product.\n" +
                    "(5) Print report\n" +
                    "(6) Exit Application");

            int userChoice = InputHandler.getUserInt("Please enter a valid option.");

            // Decides next process based on the user's choice
            switch (userChoice) {
                case 1:
                    CaptureProduct();
                    break displayMenuLoop;
                case 2:
                    SearchProduct();
                    break displayMenuLoop;
                case 3:
                    UpdateProduct();
                    break displayMenuLoop;
                case 4:
                    DeleteProduct();
                    break displayMenuLoop;
                case 5:
                    break displayMenuLoop;
                case 6:
                    ExitApp();
                    break displayMenuLoop;
                default:
                    InputHandler.displayErrorMessage("Please enter a valid option.");
            }
        }
    }

    // Manages all functionality of the Creating Product Menu
    public void CaptureProduct() {
        int productCategory;
        int productPrice;
        int productStockLevel;

        System.out.println("**************************************" +
                "CAPTURE A NEW PRODUCT\n" +
                "**************************************");

        System.out.print("Enter the product code: ");
        String productCode = InputHandler.getUserString();

        System.out.print("Enter the product name: ");
        String productName = InputHandler.getUserString();

        // Allows for continuous input from the user if they select an incorrect value.
        categoryInputLoop: while(true) {
            int[] options = {1, 2, 3, 4, 5};
            System.out.print("Select the product category:\n" +
                    "Desktop Computer - 1\n" +
                    "Laptop - 2\n" +
                    "Tablet - 3\n" +
                    "Printer - 4\n" +
                    "Gaming Console - 5\n\n");

            System.out.print("Product Category >> ");
            productCategory = InputHandler.getUserIntStrict(options, "Please select a valid Category.");

            if (productCategory == 0) continue categoryInputLoop; else break categoryInputLoop;
        }

        System.out.print("Indicate the product warranty. Enter (1) for 6 months or Any other Key for 2 years: ");
        String productWarranty = InputHandler.getUserString();

        // Continous Loops
        priceInputLoop: while(true) {
            System.out.print("Enter the price for " + productName + " >> ");
            productPrice = InputHandler.getUserInt();

            // Ensure's the correct prompt is shown based on the user's input.
            if (productPrice == 0) continue priceInputLoop; else break priceInputLoop;
        }

        stockLevelInputLoop: while(true) {
            System.out.print("Enter the stock level " + productName + " >> ");
            productStockLevel = InputHandler.getUserInt();

            // Ensure's the correct prompt is shown based on the user's input.
            if (productStockLevel == 0) continue stockLevelInputLoop; else break stockLevelInputLoop;
        }

        System.out.print("Enter the supplier for " + productName + " >> ");
        String productSupplier = InputHandler.getUserString();

        // Saves the inputted product details.
        SaveProduct(productCode, productName, productCategory, productWarranty, productPrice, productStockLevel, productSupplier);

        System.out.print("Product details has saved successfully!!!\n" +
                "Enter (1) to launch menu or any other key to exist.");

        // Using a String so it doesn't show an error message when the user doesn't input a number
        String userChoice = InputHandler.getUserString();

        if (userChoice.equals("1")) DisplayMenu(); else ExitApp();

    }

    /**
     * Creates a Product object and Stores it for later access.
     * @param code
     * @param name
     * @param category
     * @param warranty
     * @param price
     * @param stockLevel
     * @param supplier
     */
    public void SaveProduct(String code, String name, int category, String warranty, double price, int stockLevel, String supplier ) {
        Product newProduct = new Product(code, name, category, warranty, price, stockLevel, supplier);
        Products.add(newProduct);
    }

    // Manages all functionality of the Search Product Menu
    public void SearchProduct() {
        System.out.println("**************************************\n " +
                "Please enter the product code to search: ");
        String productToSearch = InputHandler.getUserString();


        System.out.println("**************************************");
        System.out.println("PRODUCT SEARCH RESULTS");
        System.out.println("**************************************");

        // Executes the Search handler with the user's query and stores the result
        List<Product> searchResult = SearchProductHandler(productToSearch);

        // Ensures the correct prompt is shown based on the search result ("searchResult").
        if (searchResult.size() != 0) {
            DisplayProductHandler(searchResult);
        }else {
            System.out.println("The product cannot be located. Invalid Product\n" +
                    "Enter (1) to launch menu or any other key to exit.");

            // Using a String so it doesn't show an error message when the user doesn't input a number
            String userChoice = InputHandler.getUserString();

            if (userChoice.equals("1")) DisplayMenu(); else ExitApp();
        }
    }

    // Manages all functionality of the Update Product Menu. (Assumes that the Product Code is unique)
    public void UpdateProduct() {
        System.out.println("**************************************\n " +
                "Please enter the product code to search: ");
        String productToSearch = InputHandler.getUserString();

        // Ensures that the desired product exists.
        List<Product> searchResult = SearchProductHandler(productToSearch);

        // Ensures the correct prompt is displayed based on the search result ("searchResult").
        if (searchResult.size() != 0) {
            // Manages the prompting and processing of the choice for the updating the Warranty.
            System.out.println("Update the warranty? (y) Yes, (n) No: ");
            String updateWarrantyChoice = InputHandler.getUserString();

            if (updateWarrantyChoice.equals("y")) {
                System.out.print("Enter new product warranty. Enter (1) for 6 months or Any other Key for 2 years: ");
                String productWarranty = InputHandler.getUserString();
                searchResult.get(0).setWarranty(productWarranty);
            }

            // Manages the prompting and processing of the choice for the updating the Product's Price.
            System.out.println("Update the product price? (y) Yes, (n) No: ");
            String updateProductPrice = InputHandler.getUserString();

            if (updateProductPrice.equals("y")) {
                System.out.println("Enter new product price: ");
                int productPrice = InputHandler.getUserInt();
                searchResult.get(0).setPrice(productPrice);
            }

            // Manages the prompting and processing of the choice for the updating the Stock Level.
            System.out.println("Update the product stock level? (y) Yes, (n) No: ");
            String updateStockLevel = InputHandler.getUserString();

            if (updateStockLevel.equals("y")) {
                System.out.println("Enter new stock level: ");
                int productPrice = InputHandler.getUserInt();
                searchResult.get(0).setStockLevel(productPrice);
            }

            System.out.println("Product details has been updated sucessfully!!!");

            // Using a String so it doesn't show an error message when the user doesn't input a number
            String userChoice = InputHandler.getUserString();

            if (userChoice.equals("1")) DisplayMenu(); else ExitApp();

        }else {
            System.out.println("Product could not be found. Returning to the Main Menue");
            DisplayMenu();
        }
    }

    // Manages all functionality for the Delete Product Menu. (Assumes the Product Code is a unique value).
    private void DeleteProduct() {
        System.out.println("**************************************\n " +
                "Please enter the product code to Delete: ");
        String productToSearch = InputHandler.getUserString();

        // Ensures the product actually exists.
        List<Product> searchResult = SearchProductHandler(productToSearch);

        DisplayProductHandler(searchResult);

        System.out.println("Enter (1) if you wish to delete, or enter Any other Key to Go Back");

        // Using a String so it doesn't show an error message when the user doesn't input a number
        String userChoice = InputHandler.getUserString();

        if (userChoice.equals("1")) DeleteProductHandler(searchResult); else DisplayMenu();

    }

    // Manages all functionality for Exiting out of the application.
    private void ExitApp() {
        System.out.println("BRIGHT FUTURES TECHNOLOGIES wishes you a warm Goodbye!");
    }

    /**
     * Manages the functionality of displaying single/multiple products to the user.
     * @param products
     */
    private void DisplayProductHandler(List<Product> products) {
        for (Product product: products) {
            System.out.println("PRODUCT CODE: " + product.Code +
                    "\nPRODUCT NAME: " + product.Name +
                    "\nPRODUCT WARRANTY: " + product.Warranty+
                    "\nPRODUCT CATEGORY: " + product.Category +
                    "\nPRODUCT PRICE: " + product.Price +
                    "\nPRODUCT STOCKLEVEL: " + product.StockLevel +
                    "\nPRODUCT SUPPLIER: " + product.Supplier);
            System.out.println("**************************************");
        }
    }

    /**
     * Manages the linear seaching for a specified Product.
     * In the event that the Product Code is not unique it will return all Products with the same Code.
     * @param productCode
     * @return
     */
    private List<Product> SearchProductHandler(String productCode) {
        List<Product> foundProducts = new ArrayList<>();

        // Searches the Products arry for matching Product codes.
        for (Product product: Products) {
            if (product.Code.equals(productCode)) foundProducts.add(product);
        }

        return foundProducts;
    }

    /**
     * Manages the searching, and deleting of a specified Product.
     * Deletes all Products that is passed to it.
     * @param products
     */
    private void DeleteProductHandler(List<Product> products) {
        for (Product product: products) {
            Products.remove(product);
        }
    }

}
