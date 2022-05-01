public class RAM
{
    private String name;
    private double freq;
    private int number;

    public RAM(String name, double freq, int number) {
        this.name = name;
        this.freq = freq;
        this.number = number;
    }

    @Override
    public String toString() {
        return "RAM{" +
                "name=" + name +
                ", freq=" + freq +
                ", number=" + number +
                '}';
    }
}
