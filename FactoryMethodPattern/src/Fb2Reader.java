public class Fb2Reader implements FileReader
{
    public Fb2Reader()
    {
        System.out.println("Opened a FB2 format app!");
    }

    public void openFile(String fileName)
    {
        System.out.println("Opened file " + fileName + " with FB2 format");
    }
}
