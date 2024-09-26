package IMAN;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Product> products;

    public Inventory() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void displayInventory(int sortBy) {
        switch (sortBy) {
            case 1:
                Collections.sort(products, (p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
                break;
            case 2:
                Collections.sort(products, Comparator.comparing(p -> {
                    if (p instanceof Electronics) {
                        return "Electronics";
                    } else if (p instanceof Clothing) {
                        return "Clothing";
                    } else {
                        return "Generic";
                    }
                }));
                break;
            default:
                System.out.println("Invalid sort option. Displaying inventory without sorting.");
        }

        System.out.println("Inventory:");

        if (sortBy == 2) {
            String currentGenre = "";
            for (Product product : products) {
                if (product instanceof Electronics && !currentGenre.equals("Electronics")) {
                    System.out.println("\n--- Electronics ---");
                    currentGenre = "Electronics";
                } else if (product instanceof Clothing && !currentGenre.equals("Clothing")) {
                    System.out.println("\n--- Clothing ---");
                    currentGenre = "Clothing";
                } else if (!(product instanceof Electronics) && !(product instanceof Clothing) && !currentGenre.equals("Generic")) {
                    System.out.println("\n--- Generic ---");
                    currentGenre = "Generic";
                }
                product.displayProductDetails();
            }
        } else {
            for (Product product : products) {
                product.displayProductDetails();
            }
        }
    }

    public void updateProductDetailsInInventory(String productName, String newName, int newQuantity, double newPrice) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                product.updateProductDetails(newName, newQuantity, newPrice);
                System.out.println("Product details updated successfully.");
                return;
            }
        }
        System.out.println("Product not found in the inventory.");
    }

    public void displayProductDetailsInInventory(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                product.displayProductDetails();
                return;
            }
        }
        System.out.println("Product not found in the inventory.");
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(products);
            System.out.println("Inventory data saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving inventory data: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            products = (List<Product>) ois.readObject();
            System.out.println("Inventory data loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading inventory data: " + e.getMessage());
        }
    }
        
    public void clearInventory() {
            products.clear();
            System.out.println("Inventory cleared.");
        }
    
    
 // Inside the Inventory class
    public void deleteProduct(Product product) {
        products.remove(product);
    }
    
 // Inside the Inventory class
    public List<Product> getProducts() {
        return products;
    }


}