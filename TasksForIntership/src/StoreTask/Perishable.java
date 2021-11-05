package StoreTask;

import java.time.LocalDate;

public interface Perishable {
    //Returns the remaining expiration time in days;
    int daysUntilExpiration(LocalDate purchaseDate);
}
