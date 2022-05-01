public class Main
{
    public static void main(String[] args)
    {
        ElectronicBook.getInstance().openBook("1984 | George Orwell", 124);
        ElectronicBook.getInstance().turnPageForward();
        ElectronicBook.getInstance().turnPageForward();
        ElectronicBook.getInstance().turnPageBackward();

        ElectronicBook.getInstance().openBook("Moby-Dick | Herman Melville",345);
        ElectronicBook.getInstance().turnPageBackward();
        ElectronicBook.getInstance().turnPageBackward();
        ElectronicBook.getInstance().turnPageBackward();
    }
}
