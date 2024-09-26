package IMAN;

import java.io.Serializable;

class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int quantity;
    private double price;

    public Product() {
        // Default constructor
    }

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public void updateProductDetails(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public void displayProductDetails() {
    	System.out.println("----------------------------------------------------------------------");
    	System.out.printf("| %-20s | %-20s | %-20s |%n", "Name", "Quantity", "Price");
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("| %-20s | %-20s | $%-19s |%n", name, quantity, price);
        System.out.println("----------------------------------------------------------------------");
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}