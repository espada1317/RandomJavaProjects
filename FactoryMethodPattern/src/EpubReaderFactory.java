public class EpubReaderFactory extends FileReaderFactory
{
    @Override
    public FileReader createReader() {
        return new EpubReader();
    }
}
