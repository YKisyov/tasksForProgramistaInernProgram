package StoreTask;

import java.time.LocalDate;

public interface DiscountMaker {
    //This interface is Part of the visitor design pattern.

    double visit(Appliance appliance, double quantity);
    double visit(Beverage appliance, double quantity);
    double visit(Clothes clothes, double quantity);
    double visit(Food food, double quantity);

}
