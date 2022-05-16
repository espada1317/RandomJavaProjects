public class Main {
    public static void main(String[] args) {
        Stock abcStock = new Stock("Twitter", 10);
        Broker broker = new Broker();

        Order buyStockOrder = new BuyStock(abcStock, 20);
        Order sellStockBigAmount = new SellStock(abcStock, 40);
        Order sellStockSmallAmount = new SellStock(abcStock, 10);

        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockBigAmount);
        broker.placeOrders();
        broker.takeOrder(sellStockSmallAmount);
        broker.placeOrders();
    }
}