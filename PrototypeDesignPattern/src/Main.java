public class Main
{
    public static void main(String[] args)
    {
        try {
            String moviePrototype = PrototypeRegistry.getInstance(PrototypeRegistry.ModelType.MOVIE).toString();
            System.out.println(moviePrototype);
            Movie movieCast = (Movie) PrototypeRegistry.getInstance(PrototypeRegistry.ModelType.MOVIE);
            System.out.println(movieCast.toString());
            Movie movieClone = movieCast.clone();
            System.out.println(movieClone.toString());

            String bookPrototype = PrototypeRegistry.getInstance(PrototypeRegistry.ModelType.BOOK).toString();
            System.out.println(bookPrototype);

            String spectaclePrototype = PrototypeRegistry.getInstance(PrototypeRegistry.ModelType.SPECTACLE).toString();
            System.out.println(spectaclePrototype);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
