import java.util.ArrayList;
import java.util.List;

public class ShopDeposit {
    private List<Product> productList;

    public ShopDeposit() {
        this.productList = new ArrayList<>();
    }

    public void addProductToDeposit(Product product) {
        this.productList.add(product);
    }

    public void removeProductToDeposit(Product product) {
        this.productList.remove(product);
    }

    public List<Product> getProductList() {
        return productList;
    }
}