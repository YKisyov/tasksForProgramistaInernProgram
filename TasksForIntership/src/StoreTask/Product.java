package StoreTask;

public abstract class Product {
    private final String name;
    private final String brand;
    private final double price;

    abstract String showOnReceipt();


    public Product(String name, String brand, double price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }
}
