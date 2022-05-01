public class Fb2ReaderFactory extends FileReaderFactory
{
    @Override
    public FileReader createReader()
    {
        return new Fb2Reader();
    }
}
