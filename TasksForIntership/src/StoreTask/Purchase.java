package StoreTask;

public class Purchase {
    private final Product item;
    private final double quantity;

    public Purchase(Product item, double quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Product getItem() {
        return item;
    }

    public double getQuantity() {
        return quantity;
    }
}
