public class WebmoneyStrategy implements PaymentStrategy{

    private String WMID;
    private String purseId;
    private double amountOnPurse;

    public WebmoneyStrategy(String WMID, String purseId, double amountOnPurse) {
        this.WMID = WMID;
        this.purseId = purseId;
        this.amountOnPurse = amountOnPurse;
    }


    @Override
    public void pay(int amount) {
        if(amount <= amountOnPurse) {
            amountOnPurse -= amount;
            System.out.println(amount +" paid with webmoney");
        } else {
            System.out.println("Declined. Not enough amount on webmoney.");
        }
    }
}
