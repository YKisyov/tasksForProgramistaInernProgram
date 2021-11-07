package StoreTask;

public class Clothes extends Product {
    private final ClothesSize size;
    protected final String color;

    public Clothes(String name, String brand, double price, ClothesSize size, String color) {
        super(name, brand, price);
        this.size = size;
        this.color = color.toLowerCase();
    }

    public ClothesSize getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    @Override
    String showOnReceipt() {
        //Generates only the fist line that has to be shown on the receipt;
        return this.getName() + " - " + this.getBrand();
    }

    @Override
    public double accept(DiscountMaker discountMaker, double productQuantity) {
        return discountMaker.visit(this, productQuantity);
    }
}
