import java.util.ArrayList;
import java.util.List;

public class Broker {
    private List<Order> orderList = new ArrayList<>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        System.out.println("Orders for broker: =============");
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
        System.out.println("================================");
    }
}