package StoreTask;

import java.util.Date;

public interface Perishable {
    //Returns the remaining expiration time in days;
    int daysUntilExpiration();
}
