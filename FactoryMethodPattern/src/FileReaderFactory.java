public abstract class FileReaderFactory
{
    public abstract FileReader createReader();

    public void openFileWithReader(String fileName)
    {
        FileReader fileReader = createReader();
        fileReader.openFile(fileName);
    }
}
