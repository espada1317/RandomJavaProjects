public class Main {
    public static void main(String args[]) {
        PublicFigure elona_mask = new PublicFigure("Elon Musk", "@elona_mask");
        PublicFigure trump = new PublicFigure("Donald Trump", "@trump");
        PublicFigure mickael_jackson = new PublicFigure("Mickael Jackson", "@jackson");

        Follower ajay = new Follower("Ajay");
        Follower vijay = new Follower("Vijay");
        Follower racheal = new Follower("Racheal");
        Follower micheal = new Follower("Micheal");
        Follower kim = new Follower("Kim");

        elona_mask.addSubscriber(ajay);
        elona_mask.addSubscriber(vijay);
        elona_mask.addSubscriber(racheal);
        elona_mask.addSubscriber(micheal);
        elona_mask.addSubscriber(kim);

        trump.addSubscriber(ajay);
        trump.addSubscriber(vijay);
        trump.addSubscriber(racheal);

        mickael_jackson.addSubscriber(racheal);
        mickael_jackson.addSubscriber(micheal);
        mickael_jackson.addSubscriber(kim);

        elona_mask.tweet("We are going to Mars!");
        trump.tweet("Make America great again!");
        elona_mask.removeSubscriber(racheal);
        mickael_jackson.tweet("I'm back!");
        elona_mask.tweet("I'm going to buy this shit and make it free");
    }
}