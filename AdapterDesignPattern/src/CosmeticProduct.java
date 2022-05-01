public class CosmeticProduct implements Product{
    String name;
    int price;
    int quantity;

    public CosmeticProduct(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getProduct() {
        return "CosmeticProduct{ name = " + getName() + " , price = " + getPrice() + " , quantity = " + getQuantity() + " }";
    }
}
