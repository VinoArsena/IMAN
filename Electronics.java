package IMAN;

class Electronics extends Product {
    private String brand;

    public Electronics(String name, int quantity, double price, String brand) {
        super(name, quantity, price);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public void displayProductDetails() {
        super.displayProductDetails();
        System.out.printf("| %-20s | %-43s |%n", "Brand: ", brand);
    	System.out.println("----------------------------------------------------------------------\n");
    }
}

