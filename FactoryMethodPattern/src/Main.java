public class Main
{
    public static void main(String[] args)
    {
        FileReaderFactory factory = new Fb2ReaderFactory();
        factory.openFileWithReader("Oscar Wilde | The Picture of Dorian Gray");

        FileReader fileReader = factory.createReader();
        fileReader.openFile("Федор Достоевский | Братья Карамазовы");

        factory = new EpubReaderFactory();
        factory.openFileWithReader("Данте Алигьери | Божественная комедия");

        fileReader = factory.createReader();
        fileReader.openFile("Франц Кафка | Процесс");

        configureFormat("UmbertoEco_CimitirulDinPraga.fb2");
        configureFormat("100LetOdinocestva_GGM.epub");
        configureFormat("DesignPatterns_AlexanderShvets.pdf");
    }

    public static void configureFormat(String fileName)
    {
        FileReaderFactory temp;
        if(fileName.contains(".fb2")) {
            temp = new Fb2ReaderFactory();
        } else if(fileName.contains(".epub")) {
            temp = new EpubReaderFactory();
        } else {
            System.out.println("We don't support this format!");
            return;
        }

        temp.openFileWithReader(fileName);
    }
}
