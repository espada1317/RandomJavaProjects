public class SellStock implements Order {
    private Stock abcStock;
    private int quantity;

    public SellStock(Stock abcStock, int value){
        this.abcStock = abcStock;
        this.quantity = value;
    }

    public void execute() {
        abcStock.sell(quantity);
    }
}