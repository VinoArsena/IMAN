package IMAN;

class Clothing extends Product {
    private String size;

    public Clothing(String name, int quantity, double price, String size) {
        super(name, quantity, price);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    @Override
    public void displayProductDetails() {
        super.displayProductDetails();
    	System.out.printf("| %-20s | %-43s |%n", "Size: ", size);
    	System.out.println("----------------------------------------------------------------------\n");
    }
}