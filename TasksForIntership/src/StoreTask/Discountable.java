package StoreTask;

public interface Discountable {
    //This interface is Part of the visitor design pattern.

    public double accept(DiscountMaker discountMaker, double productQuantity);
}
