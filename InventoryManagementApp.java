// InventoryManagementApp.java

package IMAN;

import java.io.*;
import java.util.Scanner;

public class InventoryManagementApp {

    public static void enterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Product");
            System.out.println("2. Display Inventory");
            System.out.println("3. Update Product Details");
            System.out.println("4. View Product Details");
            System.out.println("5. Save Inventory to File");
            System.out.println("6. Load Inventory from File");
            System.out.println("7. Delete Product");
            System.out.println("8. Clear Inventory");
            System.out.println("9. Exit"); 
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
                System.out.println();
                continue;
            }

            switch (choice) {
                case 1:
                    addProduct(inventory, scanner);
                    break;
                case 2:
                    System.out.println("1. Sort Alphabetically");
                    System.out.println("2. Sort by Genre");
                    System.out.print("Enter your sorting choice: ");
                    int sortChoice = scanner.nextInt();
                    System.out.println();
                    inventory.displayInventory(sortChoice);
                    enterKey();
                    break;
                case 3:
                    System.out.print("Enter the product name to update: ");
                    String updateProductName = scanner.next();
                    updateProductDetails(inventory, updateProductName, scanner);
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Enter the product name to view details: ");
                    String viewProductName = scanner.next();
                    System.out.println();
                    inventory.displayProductDetailsInInventory(viewProductName);
                    System.out.println();
                    enterKey();
                    break;
                case 5:
                    System.out.print("Enter the filename to save: ");
                    String saveFilename = scanner.next();
                    inventory.saveToFile(saveFilename);
                    System.out.println();
                    enterKey();
                    break;
                case 6:
                    System.out.print("Enter the filename to load: ");
                    String loadFilename = scanner.next();
                    inventory.loadFromFile(loadFilename);
                    System.out.println();
                    enterKey();
                    break;
                case 7:
                    System.out.print("Enter the product name to delete: ");
                    String deleteProductName = scanner.next();
                    deleteProduct(inventory, deleteProductName);
                    System.out.println();
                    enterKey();
                    break;
                case 8:
                    inventory.clearInventory();
                    System.out.println();
                    enterKey();
                    break;
                case 9:
                    System.out.println("Exiting Inventory Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
            }
        }
    }

    private static void addProduct(Inventory inventory, Scanner scanner) {
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter quantity: ");
        int quantity;
        try {
            quantity = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input for quantity. Please enter a number.");
            scanner.nextLine(); // Consume the invalid input
            System.out.println();
            enterKey();
            return;
        }

        System.out.print("Enter price: $");
        double price;
        try {
            price = scanner.nextDouble();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input for price. Please enter a number.");
            scanner.nextLine(); // Consume the invalid input
            System.out.println();
            enterKey();
            return;
        }

        System.out.print("Enter product type ([1] Electronics | [2] Clothing | [3] Generic): ");
        int productType = scanner.nextInt();

        Product product;

        if (productType == 1) {
            scanner.nextLine(); // Consume the newline character
            System.out.print("Enter brand for Electronics: ");
            String brand = scanner.nextLine();
            product = new Electronics(name, quantity, price, brand);
        } else if (productType == 2) {
            scanner.nextLine(); // Consume the newline character
            System.out.print("Enter size for Clothing: ");
            String size = scanner.nextLine();
            product = new Clothing(name, quantity, price, size);
        } else if (productType == 3) {
            System.out.println("Adding as a generic product.");
            product = new Product(name, quantity, price);
        } else {
            System.out.println("Invalid product type. Adding as a generic product.");
            product = new Product(name, quantity, price);
        }

        inventory.addProduct(product);
        System.out.println("Product added to the inventory.");
        enterKey();
    }

    private static void updateProductDetails(Inventory inventory, String productName, Scanner scanner) {
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter new product name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter new quantity: ");
        int newQuantity;
        try {
            newQuantity = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input for quantity. Please enter a number.");
            scanner.nextLine(); // Consume the invalid input
            System.out.println();
            enterKey();
            return;
        }

        System.out.print("Enter new price: $");
        double newPrice;
        try {
            newPrice = scanner.nextDouble();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input for price. Please enter a number.");
            scanner.nextLine(); // Consume the invalid input
            System.out.println();
            enterKey();
            return;
        }

        inventory.updateProductDetailsInInventory(productName, newName, newQuantity, newPrice);
        enterKey();
    }

    private static void deleteProduct(Inventory inventory, String productName) {
        boolean productFound = false;

        for (Product product : inventory.getProducts()) {
            if (product.getName().equals(productName)) {
                inventory.deleteProduct(product);
                System.out.println("Product deleted successfully.");
                productFound = true;
                break;
            }
        }

        if (!productFound) {
            System.out.println("Product not found in the inventory. Deletion failed.");
        }
    }
}
