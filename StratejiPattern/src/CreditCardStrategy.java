import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditCardStrategy implements PaymentStrategy {

    private String name;
    private String cardNumber;
    private String cvv;
    private String dateOfExpiry;
    private double amountOnCard;

    public CreditCardStrategy(String nm, String ccNum, String cvv, String expiryDate, double amountOnCard){
        this.name = nm;
        this.cardNumber = ccNum;
        this.cvv = cvv;
        this.dateOfExpiry = expiryDate;
        this.amountOnCard = amountOnCard;
    }

    public Date transformStringToDate(String text) {
        Date date;

        String[] splits = text.split("/");
        int [] arr = new int [3];
        for(int i = 0; i < splits.length; i++) {
            arr[i] = Integer.parseInt(splits[i]);
        }
        date = new Date(arr[2], arr[1] - 1, arr[0]);

        return date;
    }

    @Override
    public void pay(int amount){

        Date expiry_date = transformStringToDate(dateOfExpiry);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String current_date = formatter.format(date);
        Date currentDate = transformStringToDate(current_date);

        if(currentDate.before(expiry_date))
        {
            if(amount <= amountOnCard) {
                amountOnCard -= amount;
                System.out.println(amount +" paid with credit/debit card");
            } else {
                System.out.println("Declined. Not enough amount on card.");
            }
        } else {
            System.out.println("Card expired.");
        }
    }
}