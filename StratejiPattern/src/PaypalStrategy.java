public class PaypalStrategy implements PaymentStrategy {

    private String emailId;
    private String password;
    private double amountOnAccount;

    public PaypalStrategy(String email, String pwd, int amountOnAccount){
        this.emailId = email;
        this.password = pwd;
        this.amountOnAccount = amountOnAccount;
    }

    @Override
    public void pay(int amount) {
        if(amount <= amountOnAccount) {
            amountOnAccount -= amount;
            System.out.println(amount +" paid with paypal");
        } else {
            System.out.println("Declined. Not enough amount on paypal.");
        }
    }

}