package StoreTask;

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


        ProductsList productsList01 = new ProductsList(item01, 2.45d);
        ProductsList productsList02 = new ProductsList(item02, 3.d);
        ProductsList productsList03 = new ProductsList(item03, 2.d);
        ProductsList productsList04 = new ProductsList(item04, 1.d);

        List<ProductsList> cart = new ArrayList<>();
        cart.add(productsList01);
        cart.add(productsList02);
        cart.add(productsList03);
        cart.add(productsList04);

        printReceipt(cart, LocalDateTime.parse("2021-06-14T12:34:56"));

    }

    public static void printReceipt(List<ProductsList> cart, LocalDateTime purchaseDate) {

        double subtotal = 0.d;
        double totalDiscounts = 0.d;

        System.out.printf("Date: %s %s\n", purchaseDate.toLocalDate(), purchaseDate.toLocalTime());
        System.out.println("---Products---");

        //Init the discount calculator (visitor pattern):
        DiscountCalculator discountMaker = new DiscountCalculator(purchaseDate.toLocalDate());

        for (ProductsList productsList : cart) {
            System.out.println();
            System.out.println(productsList.getItem().showOnReceipt());

            System.out.printf("%.2f x $%.2f = $%.2f\n",
                    productsList.getQuantity(), productsList.getItem().getPrice(),
                    productsList.getQuantity() * productsList.getItem().getPrice());

            subtotal += productsList.getItem().getPrice() * productsList.getQuantity();

            /*
             * Checking for discounts via the visitor pattern;
             */
            double currentApplicableDiscount = productsList.getItem().accept(discountMaker, productsList.getQuantity());
            if (currentApplicableDiscount > 0.d){
                //Add the currentApplicableDiscount to the total discounts:
                totalDiscounts += currentApplicableDiscount;
                //Print out the line showing the applied discount;
                System.out.println(discountMaker.getDiscountTextToDisplay());
            }




            /*
            Old code:
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
            }*/
        }
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("SUBTOTAL: $%.2f\n" +
                "DISCOUNT: -$%.2f\n" +
                "TOTAL: $%.2f", subtotal, totalDiscounts, subtotal - totalDiscounts);

    }
}
