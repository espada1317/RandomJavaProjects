public class EpubReader implements FileReader
{
    public EpubReader()
    {
        System.out.println("Opened a EPUB format app!");
    }

    public void openFile(String fileName)
    {
        System.out.println("Opened file " + fileName + " with EPUB format");
    }
}
