public class Context {

    static PaymentStrategy paymentStrategy = null;

    public static PaymentStrategy getStrategy(String strategy) {

        switch (strategy) {
            case "Paypal": {
                paymentStrategy = new PaypalStrategy("myemail@example.com", "mypwd", 140);
                break;
            }
            case "CreditCard": {
                paymentStrategy = new CreditCardStrategy("Pankaj Kumar", "1234567890123456", "786", "10/6/2022", 300);
                break;
            }
            case "Webmoney": {
                paymentStrategy = new WebmoneyStrategy("464889785562", "Z646113961871", 90);
            }
        }

        return paymentStrategy;
        }
}