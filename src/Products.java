import java.util.List;
import java.util.ArrayList;

public class Products {

    public List<Product> Products = new ArrayList<Product>();

    public void DisplayMenu() {
//        FIND POSTED DATE
        /**
         * Line 16 & 31 (Labeling of While Loop - "displayMenuLoop"), was Adapted from a Java Point blog posted on 13/09/2022
         * Blog: https://www.javatpoint.com/labeled-loop-in-java
         */
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

            switch (userChoice) {
                case 1:
                    CaptureProduct();
                    break displayMenuLoop;
                case 2:
                    SearchProduct();
                    break;
                case 3:
                    UpdateProduct();
                    break;
                case 4:
                    DeleteProduct();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    InputHandler.displayErrorMessage("Please enter a valid option.");
            }
        }
    }

    public void CaptureProduct() {
        int productCategory;
        System.out.println("**************************************" +
                "CAPTURE A NEW PRODUCT\n" +
                "**************************************");

        System.out.print("Enter the product code: ");
        String productCode = InputHandler.getUserString();

        System.out.print("Enter the product name: ");
        String productName = InputHandler.getUserString();

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

        // FIX
        System.out.print("Indicate the product warranty. Enter (1) for 6 months or Any other Key for 2 years: ");
        String productWarranty = InputHandler.getUserString();

        System.out.print("Enter the price for " + productName + " >> ");
        int productPrice = InputHandler.getUserInt();

        System.out.print("Enter the stock level " + productName + " >> ");
        int productStockLevel = InputHandler.getUserInt();

        System.out.print("Enter the supplier for " + productName + " >> ");
        String productSupplier = InputHandler.getUserString();

        SaveProduct(productCode, productName, productCategory, productWarranty, productPrice, productStockLevel, productSupplier);

        System.out.print("Product details has saved successfully!!!\n" +
                "Enter (1) to launch menu or any other key to exist.");

        // Using a String so it doesn't show an error message when the user doesn't input a number
        String userChoice = InputHandler.getUserString();

        if (userChoice.equals("1")) DisplayMenu();

    }

    public void SaveProduct(String code, String name, int category, String warranty, double price, int stockLevel, String supplier ) {
        Product newProduct = new Product(code, name, category, warranty, price, stockLevel, supplier);
        Products.add(newProduct);
    }

    public void SearchProduct() {
        System.out.println("**************************************\n " +
                "Please enter the product code to search: ");
        String productToSearch = InputHandler.getUserString();


        System.out.println("**************************************");
        System.out.println("PRODUCT SEARCH RESULTS");
        System.out.println("**************************************");

        List<Product> searchResult = SearchProductHandler(productToSearch);

        if (searchResult.size() != 0) {
            DisplayProductHandler(searchResult);
        }else {
            System.out.println("The product cannot be located. Invalid Product\n" +
                    "Enter (1) to launch menu or any other key to exit.");

            // Using a String so it doesn't show an error message when the user doesn't input a number
            String userChoice = InputHandler.getUserString();

            if (userChoice.equals("1")) DisplayMenu(); else return;
        }

    }

    public void UpdateProduct() {
        System.out.println("**************************************\n " +
                "Please enter the product code to search: ");
        String productToSearch = InputHandler.getUserString();
        List<Product> searchResult = SearchProductHandler(productToSearch);

        if (searchResult.size() != 0) {
            System.out.println("Update the warranty? (y) Yes, (n) No: ");
            String updateWarrantyChoice = InputHandler.getUserString();

            if (updateWarrantyChoice.equals("y")) {
                System.out.print("Enter new product warranty. Enter (1) for 6 months or Any other Key for 2 years: ");
                String productWarranty = InputHandler.getUserString();
                searchResult.get(0).setWarranty(productWarranty);
            }

            System.out.println("Update the product price? (y) Yes, (n) No: ");
            String updateProductPrice = InputHandler.getUserString();

            if (updateProductPrice.equals("y")) {
                System.out.println("Enter new product price: ");
                int productPrice = InputHandler.getUserInt();
                searchResult.get(0).setPrice(productPrice);
            }

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
            if (userChoice.equals("1")) DisplayMenu();
        }else {
            System.out.println("Product could not be found.");
            DisplayMenu();
        }
    }

    private void DeleteProduct() {
        System.out.println("**************************************\n " +
                "Please enter the product code to Delete: ");
        String productToSearch = InputHandler.getUserString();

        List<Product> searchResult = SearchProductHandler(productToSearch);

        DisplayProductHandler(searchResult);

        System.out.println("Enter (1) if you wish to delete, or enter Any other Key to Go Back");
        // Using a String so it doesn't show an error message when the user doesn't input a number
        String userChoice = InputHandler.getUserString();

        if (userChoice.equals("1")) DeleteProductHandler(searchResult); else DisplayMenu();

    }

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

    // Assumes Product Code is not unique
    private List<Product> SearchProductHandler(String productCode) {
        List<Product> foundProducts = new ArrayList<>();

        for (Product product: Products) {
            if (product.Code.equals(productCode)) foundProducts.add(product);
        }

        return foundProducts;
    }

    private void DeleteProductHandler(List<Product> products) {
        for (Product product: products) {
            Products.remove(product);
        }
    }

}
