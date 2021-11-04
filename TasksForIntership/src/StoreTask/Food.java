package StoreTask;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Food extends Product implements Perishable{

    private final LocalDate expDate;

    public Food(String name, String brand, double price, LocalDate expirationDate) {
        super(name, brand, price);
        this.expDate = expirationDate;
    }

    @Override
    public int daysUntilExpiration() {
        return (int) expDate.until(LocalDate.now(), ChronoUnit.DAYS) ;
    }

    @Override
    String showOnReceipt() {
        //Generates only the fist line that has to be shown on the receipt;
        return this.getName() + " - " + this.getBrand();
    }
}
