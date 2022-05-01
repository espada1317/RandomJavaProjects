public class GroceryProductAdapter implements Product {

    private GroceryProduct groceryProduct;

    public GroceryProductAdapter(GroceryProduct groceryProduct) {
        this.groceryProduct = groceryProduct;
    }

    @Override
    public String getName() {
        return groceryProduct.getName();
    }

    @Override
    public int getPrice() {
        return groceryProduct.getPrice();
    }

    @Override
    public int getQuantity() {
        return groceryProduct.getQuantity();
    }

    @Override
    public String getProduct() {
        return ("GroceryProduct{ name = " + groceryProduct.getName() + " , price = " + groceryProduct.getPrice() + " , quantity = " + groceryProduct.getQuantity() + " }");
    }
}
