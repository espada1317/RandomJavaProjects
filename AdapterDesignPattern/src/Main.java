import java.util.List;

public class Main {
    public static void main(String[] args) {
        ShopDeposit inventory = new ShopDeposit();

        inventory.addProductToDeposit(new CosmeticProduct("Lavie Handbag", 5000, 2));
        inventory.addProductToDeposit(new FitnessProduct("Yoga SmartFit", 2000, 10));

        GroceryProduct groceryItem = new GroceryProduct("Wheat Flour", 100, 100);
        inventory.addProductToDeposit(new GroceryProductAdapter(groceryItem));

        List<Product> listProducts = inventory.getProductList();

        for(Product product: listProducts)
        {
            System.out.println(" - " + product.getProduct());
        }
    }
}