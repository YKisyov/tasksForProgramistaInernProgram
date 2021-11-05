package StoreTask;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cashier {
    public static void main(String[] args) {

        //Picking up items from shelves :-)
        Food item01 = new Food("apples", "BrandA", 1.50d, LocalDate.parse("2021-06-14"));
        Beverage item02 = new Beverage("milk", "BrandM", .99d, LocalDate.parse("2022-02-02"));
        Clothes item03 = new Clothes("T-shirt", "BrandT", 15.99d,
                ClothesSize.M, "violet");
        Appliance item04 = new Appliance("laptop", "BrandL", 2345.d,
                "ModelL", LocalDate.parse("2021-03-03"), 1.125d);

        Cart cart01 = new Cart(item01, 2.45d);
        Cart cart02 = new Cart(item02, 3.d);
        Cart cart03 = new Cart(item03, 2.d);
        Cart cart04 = new Cart(item04, 1.d);

        List<Cart> cart = new ArrayList<>();
        cart.add(cart01);
        cart.add(cart02);
        cart.add(cart03);
        cart.add(cart04);

        printReceipt(cart, LocalDateTime.parse("2021-06-14T12:34:56"));

    }

    public static void printReceipt(List<Cart> cart, LocalDateTime purchaseDate) {
        //TODO show the date displayed on the cart/receipt:

        double subtotal = 0.d;
        double totalDiscounts = 0.d;
        System.out.printf("Date: %s %s\n", purchaseDate.toLocalDate(), purchaseDate.toLocalTime());

        System.out.println("---Products---");
        for (Cart purchase : cart) {
            System.out.println();
            System.out.println(purchase.getItem().showOnReceipt());
            // Add two empty lines:
            System.out.printf("%.2f x $%.2f = $%.2f\n",
                    purchase.getQuantity(), purchase.getItem().getPrice(),
                    purchase.getQuantity() * purchase.getItem().getPrice());

            subtotal += purchase.getItem().getPrice();

            /*
             * Checking for discounts.
             *
             *
             * */
            if (purchase.getItem() instanceof Perishable) {
                //Check to see if discount is applicable for this purchase
                if (((Perishable) purchase.getItem()).daysUntilExpiration(purchaseDate.toLocalDate()) <= 1) {
                    //Apply a 50 % discount:
                    double currentDiscount = purchase.getItem().getPrice() * purchase.getQuantity() * 0.5d;
                    totalDiscounts += currentDiscount;
                    //print out the discount:
                    System.out.printf("#discount 50%%  -$%.2f\n", currentDiscount);
                } else if (((Perishable) purchase.getItem()).daysUntilExpiration(purchaseDate.toLocalDate()) <= 5) {
                    //Apply a 10 % discount:
                    double currentDiscount = purchase.getItem().getPrice() * purchase.getQuantity() * 0.1d;
                    totalDiscounts += currentDiscount;
                    //print out the discount:
                    System.out.printf("#discount 10%%  -$%.2f\n", currentDiscount);
                }
            } else if (purchase.getItem() instanceof Clothes) {
                //Check the purchase date and see if it is a working day
                if (DayOfWeek.MONDAY.getValue() <= purchaseDate.getDayOfWeek().getValue()
                        && purchaseDate.getDayOfWeek().getValue() <= DayOfWeek.FRIDAY.getValue()) {
                    //Apply a 10 % discount:
                    double currentDiscount = purchase.getItem().getPrice() * purchase.getQuantity() * 0.1d;
                    totalDiscounts += currentDiscount;
                    //print out the discount:
                    System.out.printf("#discount 10%%  -$%.2f\n", currentDiscount);
                }
            } else if (purchase.getItem() instanceof Appliance) {
                if (purchase.getItem().getPrice() > 999
                        && (DayOfWeek.SATURDAY.getValue() == purchaseDate.getDayOfWeek().getValue()
                        || purchaseDate.getDayOfWeek().getValue() == DayOfWeek.SUNDAY.getValue())) {
                    //Apply 5% discount:
                    double currentDiscount = purchase.getItem().getPrice() * purchase.getQuantity() * 0.05d;
                    totalDiscounts += currentDiscount;
                    //print out the discount:
                    System.out.printf("#discount 5%%  -$%.2f\n", currentDiscount);
                }
            }
        }
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("SUBTOTAL: $%.2f\n" +
                "DISCOUNT: -$%.2f\n" +
                "TOTAL: $%.2f", subtotal, totalDiscounts, subtotal - totalDiscounts);

    }
}
