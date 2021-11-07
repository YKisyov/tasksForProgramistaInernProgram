package StoreTask;

public class ProductsList {
    //This class bonds together the concrete product and its quantity that is going to be added to a shopping cart.
    private final Product item;
    private final double quantity;

    public ProductsList(Product item, double quantity) {
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
