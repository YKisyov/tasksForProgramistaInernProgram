package StoreTask;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Beverage extends Product implements Perishable {
    private final LocalDate expDate;

    public Beverage(String name, String brand, double price, LocalDate expirationDate) {
        super(name, brand, price);
        this.expDate = expirationDate;
    }

    @Override
    public int daysUntilExpiration() {
        LocalDate today = LocalDate.now();
        return (int) expDate.until(LocalDate.now(), ChronoUnit.DAYS) ;
    }

    @Override
    String showOnReceipt() {
        //Generates only the fist line that has to be shown on the receipt;
        return this.getName() + " - " + this.getBrand();
    }
}
