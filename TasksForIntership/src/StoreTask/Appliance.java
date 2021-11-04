package StoreTask;

import java.time.LocalDate;

public class Appliance extends Product {
    private final String model;
    private final LocalDate manufacturingDate;
    private final double weight;


    public Appliance(String name, String brand, double price, String model, LocalDate manufacturingDate, double weight) {
        super(name, brand, price);
        this.model = model;
        this.manufacturingDate = manufacturingDate;
        this.weight = weight;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    String showOnReceipt() {
        //Generates only the fist line that has to be shown on the receipt;
        return this.getName() + " - " + this.getBrand();
    }
}
