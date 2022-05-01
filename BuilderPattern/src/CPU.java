public class CPU
{
    private String name;
    private double freq;
    private int nmbThreads;

    public CPU(String name, double freq, int nmbThreads)
    {
        this.name = name;
        this.freq = freq;
        this.nmbThreads = nmbThreads;
    }

    @Override
    public String toString() {
        return "CPU{" +
                "name=" + name  +
                ", freq=" + freq +
                ", nmbThreads=" + nmbThreads +
                '}';
    }
}
