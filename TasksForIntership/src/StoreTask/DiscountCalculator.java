package StoreTask;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DiscountCalculator implements DiscountMaker {
    //This class is Part of the visitor design pattern.

    /*
    All discounts are subject to store rules, terms and conditions.

    * TYPE_01 discount is applicable for Products of type Food & Beverages, whose expiration date is within 5 days of
    the purchase date with exception to the case where expiration date and purchase date are the same - then TYPE_02
    discount is applied.

    * TYPE_02 discount is applicable to Products of type Food and Beverages where the expiration date matches the
    purchase date and its size is governed by the store owners. It is currently 50% of the item price.

    * Appliance weekend discount is applied to all Products that are purchased during the weekend and whose price
    is above $999. Discount for these appliances is 5%.

    * Clothes are discounted by a flat discount rare of 10% if purchases is made during Monday to Fridays days.
    * */

    private String discountTextToDisplay = null;

    private final double DISCOUNT_FOR_APPLIANCE_WEEKEND = 0.05d; // in percents
    private final double DISCOUNT_FOR_BEVERAGES_AND_FOOD_TYPE_01 = 0.10d; // in percents, due date -> 5 days
    private final double DISCOUNT_FOR_BEVERAGES_AND_FOOD_TYPE_02 = 0.5d; // in percents, due date -> 1 day
    private final double DISCOUNT_FOR_CLOTHES = 0.1d; // in percents

    /*
    Typically, a constructor is now needs, but I'm going to use it to pass some discount specific parameters,
    like the purchaseDate, that is used by some discount methods to determine whether a discount is
    applicable.
    */
    private final LocalDate purchaseDate;

    DiscountCalculator(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public double visit(Appliance appliance, double qty) {
        if (appliance.getPrice() > 999.d
                && (purchaseDate.getDayOfWeek().getValue() == DayOfWeek.SATURDAY.getValue()
                || purchaseDate.getDayOfWeek().getValue() == DayOfWeek.SUNDAY.getValue())) {
            //This item is eligible for 5% discount;
            double calculatedDiscount = appliance.getPrice() * DISCOUNT_FOR_APPLIANCE_WEEKEND * qty;
            discountTextToDisplay = String.format("#discount %.0f%%  -$%.2f",
                    DISCOUNT_FOR_APPLIANCE_WEEKEND * 100.d, calculatedDiscount);
            return calculatedDiscount;
        }
        return 0.d;
    }

    @Override
    public double visit(Beverage beverage, double qty) {
        if (beverage.daysUntilExpiration(purchaseDate) <= 1) {
            //This purchase is applicable for 50%
            double calculatedDiscount = beverage.getPrice() * DISCOUNT_FOR_BEVERAGES_AND_FOOD_TYPE_02 * qty;
            discountTextToDisplay = String.format("#discount %.0f%%  -$%.2f",
                    DISCOUNT_FOR_BEVERAGES_AND_FOOD_TYPE_02 * 100.d, calculatedDiscount);
            return calculatedDiscount;
        } else if (beverage.daysUntilExpiration(purchaseDate) <= 5) {
            //This purchase is applicable for 10%
            double calculatedDiscount = beverage.getPrice() * DISCOUNT_FOR_BEVERAGES_AND_FOOD_TYPE_01 * qty;
            discountTextToDisplay = String.format("#discount %.0f%%  -$%.2f",
                    DISCOUNT_FOR_BEVERAGES_AND_FOOD_TYPE_01 * 100.d, calculatedDiscount);
            return calculatedDiscount;
        }
        return 0.d;
    }

    @Override
    public double visit(Clothes clothes, double qty) {
        if (DayOfWeek.MONDAY.getValue() >= purchaseDate.getDayOfWeek().getValue()
                && purchaseDate.getDayOfWeek().getValue() <= DayOfWeek.FRIDAY.getValue()) {
            //This purchase is applicable for 5%
            double calculatedDiscount = clothes.getPrice() * DISCOUNT_FOR_CLOTHES * qty;
            discountTextToDisplay = String.format("#discount %.0f%%  -$%.2f",
                    DISCOUNT_FOR_CLOTHES * 100.d, calculatedDiscount);
            return calculatedDiscount;
        }
        return 0.d;
    }

    @Override
    public double visit(Food food, double qty) {
        if (food.daysUntilExpiration(purchaseDate) <= 1) {
            //This purchase is applicable for 50%
            double calculatedDiscount = food.getPrice() * DISCOUNT_FOR_BEVERAGES_AND_FOOD_TYPE_02 * qty;
            discountTextToDisplay = String.format("#discount %.0f%%  -$%.2f",
                    DISCOUNT_FOR_BEVERAGES_AND_FOOD_TYPE_02 * 100.d, calculatedDiscount);
            return calculatedDiscount;
        } else if (food.daysUntilExpiration(purchaseDate) <= 5) {
            //This purchase is applicable for 10%
            double calculatedDiscount = food.getPrice() * DISCOUNT_FOR_BEVERAGES_AND_FOOD_TYPE_01 * qty;
            discountTextToDisplay = String.format("#discount %.0f%%  -$%.2f",
                    DISCOUNT_FOR_BEVERAGES_AND_FOOD_TYPE_01 * 100.d, calculatedDiscount);
            return calculatedDiscount;
        }
        return 0.d;

    }
    public String getDiscountTextToDisplay() {
        return discountTextToDisplay;
    }

}