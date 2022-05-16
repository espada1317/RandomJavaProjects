public class BuyStock implements Order {
    private Stock abcStock;
    private int quantity;

    public BuyStock(Stock abcStock, int value){
        this.abcStock = abcStock;
        this.quantity = value;
    }

    public void execute() {
        abcStock.buy(quantity);
    }
}