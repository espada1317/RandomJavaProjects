public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item("Durex",10);
        Item item2 = new Item("Vizit",40);
        Item item3 = new Item("Skyn",80);

        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item3);

        cart.pay(Context.getStrategy("Paypal"));
        cart.pay(Context.getStrategy("CreditCard"));
        cart.pay(Context.getStrategy("Webmoney"));
    }
}
