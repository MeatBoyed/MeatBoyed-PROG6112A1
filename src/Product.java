/**
 * All details about a specific product is store within the Product object.
 * This is done to comply with OOP standards.
 *
 * All Switch statment using Lambdas, was Adapted from the Vojtech Ruzicka's Programming Blog posted on 07/04/2019
 * Blog: https://www.w3schools.com/java/java_try_catch.asp
 */
public class Product {
    public String Code;
    public String Name;

    public String Category;
    public String Warranty;

    public double Price;
    public int StockLevel;
    public String Supplier;

    /**
     * Constructs a Product object and manages necessary conversions from user input to stored values.
     * @param code
     * @param name
     * @param category
     * @param warranty
     * @param price
     * @param stockLevel
     * @param supplier
     */
    public Product(String code, String name, int category, String warranty, double price, int stockLevel, String supplier) {
        Code = code;
        Name = name;
        Price = price;
        Supplier = supplier;
        StockLevel = stockLevel;

        /**
         * The passed "category" & "warranty" is converted into the respective value, and stored in the "Category" field to
         * Reduce Complexity in the Product class.
         */
        Warranty = switch (warranty) {
            case "1" -> "6 months";
            default -> "2 years";
        };
        Category = switch (category) {
            case 1 -> "Desktop Computer";
            case 2 -> "Laptop";
            case 3 -> "Tablet";
            case 4 -> "Printer";
            case 5 -> "Gaming Console";
            default -> "";
        };
    }

    public String getCode() {
        return Code;
    }

    public String getName() {
        return Name;
    }

    public String getCategory() {
        return Category;
    }

    public String getWarranty() {
        return Warranty;
    }

    public double getPrice() {
        return Price;
    }

    public int getStockLevel() {
        return StockLevel;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setWarranty(String warranty) {
        Warranty = switch (warranty) {
            case "1" -> "6 months";
            default -> "2 years";
        };
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setStockLevel(int stockLevel) {
        StockLevel = stockLevel;
    }
}
