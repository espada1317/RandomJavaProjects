public class Stock {

    private String name;
    private int initialAmount;

    public static int totalQuantity;

    public Stock(String name, int initialAmount) {
        this.name = name;
        this.initialAmount = initialAmount;
        totalQuantity = initialAmount;
    }

    public void buy(int value){
        totalQuantity += value;
        System.out.println("Stock [ Name: "+name+", Quantity: " + value +" ] bought. Have in total: " + totalQuantity);
    }
    public void sell(int value){
        totalQuantity -= value;
        System.out.println("Stock [ Name: "+name+", Quantity: " + value +" ] sold. Have in total: " + totalQuantity);
    }
}