public class ElectronicBook
{
    private static ElectronicBook instance = null;
    public String BookName;
    public int Page;

    private ElectronicBook()
    {
        System.out.println("ElectronicBook was turned on!");
    }

    public static synchronized ElectronicBook getInstance()
    {
        if(instance == null)
        {
            instance = new ElectronicBook();
        }
        return instance;
    }

    public void openBook(String bookName, int page)
    {
        this.BookName = bookName;
        this.Page = page;
        System.out.println("Opened book: " + BookName + " on page " + Page);
    }

    public void turnPageForward()
    {
        this.Page++;
        System.out.println("Turned book on page " + Page);
    }

    public void turnPageBackward()
    {
        this.Page--;
        System.out.println("Turned book on page " + Page);
    }
}
